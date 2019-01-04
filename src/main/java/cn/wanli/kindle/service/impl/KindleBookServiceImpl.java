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
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.persistence.KindleBookRepository;
import cn.wanli.kindle.service.KindleBookService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author wanli
 * @date 2019-01-04 00:59
 */
@Service
public class KindleBookServiceImpl implements KindleBookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KindleBookServiceImpl.class);

    @Autowired
    private KindleBookRepository bookRepository;

    @Override
    public PaginationData<KindleBook> pageBooks(int requestPage, int pageSize, String keyword) {
        PaginationData<KindleBook> data;
        Page<KindleBook> page;
        if (Strings.isBlank(keyword)) {
            page = bookRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = bookRepository.findAllByNameContaining(keyword, PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        data = new PaginationData<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getContent());
        return data;
    }
}
