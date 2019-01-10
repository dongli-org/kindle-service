/*
 * kindle electronic book push service
 *
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanli.kindle.service.impl;

import cn.wanli.kindle.domain.KindleBook;
import cn.wanli.kindle.entity.BookEntity;
import cn.wanli.kindle.entity.KindleBookEntity;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.meta.EXTHRecord;
import cn.wanli.kindle.meta.MobiMeta;
import cn.wanli.kindle.meta.StreamUtils;
import cn.wanli.kindle.persistence.KindleBookRepository;
import cn.wanli.kindle.service.KindleBookService;
import cn.wanli.kindle.service.UserService;
import cn.wanli.kindle.utils.CommonsUtils;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author wanli
 * @date 2019-01-04 00:59
 */
@Service
public class KindleBookServiceImpl implements KindleBookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KindleBookServiceImpl.class);
    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    @Value("${kindle.root-path}")
    private String rootPath;
    @Value("${kindle.image-path}")
    private String imagePath;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private KindleBookRepository bookRepository;

    @Override
    public PaginationData<KindleBookEntity> pageBooks(int requestPage, int pageSize, String keyword) {
        PaginationData<KindleBookEntity> data;
        Page<KindleBook> page;
        if (Strings.isBlank(keyword)) {
            page = bookRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = bookRepository.findAllByTitleContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        List<KindleBookEntity> entityList = page.getContent().stream()
                .map(book -> new KindleBookEntity(book.getId(), book.getTitle(), book.getPicture(), book.getPath()))
                .collect(toList());
        data = new PaginationData<>(page.getNumber(), page.getSize(), page.getTotalElements(), entityList);
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookEntity addBook(Long uid, MultipartFile source) {

        KindleBook book = new KindleBook();
        try {
            //解析Mobi
            String filePath = CommonsUtils.saveFile(source, rootPath, imagePath);

            String filename = "kindle" + System.currentTimeMillis() + source.getOriginalFilename().substring(source.getOriginalFilename().lastIndexOf('.'));

            File f = new File(rootPath + filePath + filename);
            try (FileOutputStream fos = new FileOutputStream(f);
                 FileInputStream fs = (FileInputStream) source.getInputStream()) {
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fs.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                LOGGER.error("保存文件出现错误", e);
            }

            MobiMeta meta = new MobiMeta(f);
            book.setTitle(meta.getFullName());
            book.setDateTime(LocalDateTime.now());
            book.setPath(filePath + filename);
            userService.findById(uid).ifPresent(book::setUser);

            String encoding = meta.getCharacterEncoding();
            List<EXTHRecord> exthList = meta.getEXTHRecords();
            String asin = null;
            String oldasin = null;
            for (EXTHRecord rec : exthList) {
                switch (rec.getRecordType()) {
                    case 100:
                        book.setAuthors(StreamUtils.byteArrayToString(rec.getData(), encoding));
                        break;
                    case 101:
                        book.setPublisher(StreamUtils.byteArrayToString(rec.getData(), encoding));
                        break;
                    case 104:
                        asin = StreamUtils.byteArrayToString(rec.getData(), encoding);
                        break;
                    case 113:
                        oldasin = StreamUtils.byteArrayToString(rec.getData(), encoding);
                        break;

                    default:
                        break;
                }
            }
            if (oldasin == null) {
                LOGGER.info("设置asin");
                book.setAsin(asin);
            } else {
                LOGGER.info("设置old asin");
                book.setAsin(oldasin);
            }

            //联网获取亚马逊官网信息
            String url = "https://www.amazon.cn/gp/search-inside/service-data";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("asin", book.getAsin());
            map.add("method", "getBookData");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            ResponseBook rb = JSON.parseObject(response.getBody(), ResponseBook.class);
            if (rb.authorNameList != null) {
                book.setAuthors(rb.authorNameList);
            }
            if (rb.title != null) {
                book.setTitle(rb.title);
            }
            book.setPicture(rb.thumbnailImage.replace("_SL75_.", ""));
            book.setPrice(rb.buyingPrice);

            book = bookRepository.save(book);

        } catch (Exception e) {
            LOGGER.error("出现错误啦", e);
        }

        BookEntity entity = new BookEntity(book.getId(), book.getUser().getName(), null,
                book.getTitle(), book.getAsin(), book.getAuthors(), book.getPicture());
        entity.setDate(book.getDateTime().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
        return entity;
    }

    static class ResponseBook {
        private String title;
        private String thumbnailImage;
        private String authorNameList;
        private String buyingPrice;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }

        public String getAuthorNameList() {
            return authorNameList;
        }

        public void setAuthorNameList(String authorNameList) {
            this.authorNameList = authorNameList;
        }

        public String getBuyingPrice() {
            return buyingPrice;
        }

        public void setBuyingPrice(String buyingPrice) {
            this.buyingPrice = buyingPrice;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
