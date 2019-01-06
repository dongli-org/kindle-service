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

package cn.wanli.kindle.controller;

import cn.wanli.kindle.entity.KindleBookEntity;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.service.KindleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanli
 * @date 2018-12-07 11:00
 */
@RestController
@RequestMapping("/v1/kindles")
public class KindleController {

    @Autowired
    private KindleBookService bookService;

    @GetMapping
    public PaginationData<KindleBookEntity> pageKindleSource(@RequestParam(defaultValue = "1") int requestPage,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam String keyword) {
        return bookService.pageBooks(requestPage, pageSize, keyword);
    }

}
