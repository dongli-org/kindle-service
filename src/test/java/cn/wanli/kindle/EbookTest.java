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

package cn.wanli.kindle;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wanli
 * @date 2019-01-07 23:30
 */
public class EbookTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.amazon.cn/gp/search-inside/service-data";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("asin", "B07893VGR5");
        map.add("method", "getBookData");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody().trim());
        String trim = response.getBody().trim();
        Map ret = JSON.parseObject(trim, Map.class);
        System.out.println(ret.get("kindleAsin"));
        System.out.println(ret.get("title"));
        System.out.println(ret.get("thumbnailImage").toString().replace("_SL75_.", ""));
        System.out.println(ret.get("authorNameList"));
        System.out.println(ret.get("buyingPrice"));

        System.out.println(JSON.parseObject(response.getBody(), ResponseBook.class));

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
