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

package cn.wanli.kindle.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author wanli
 * @date 2019-01-05 21:40
 */
public final class PaginationUtils {
    private PaginationUtils() {
        throw new AssertionError();
    }

    public static <R> Page<R> getPaginationData(PageInterface<R> f1, PageKeyInterface<R> f2,
                                                Integer requestPage, Integer pageSize, String keyword) {
        Page<R> page;
        if (Strings.isBlank(keyword)) {
            page = f1.page(PageRequest.of(requestPage, pageSize));
        } else {
            page = f2.page(PageRequest.of(requestPage, pageSize), keyword);
        }
        return page;
    }
}
