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

package cn.wanli.kindle.service;

import cn.wanli.kindle.domain.KindleBook;
import cn.wanli.kindle.entity.PaginationData;

/**
 * @author wanli
 * @date 2019-01-04 00:59
 */
public interface KindleBookService {

    /**
     * 获取分页kindle书籍
     *
     * @param requestPage 请求页数
     * @param pageSize    每页数据量
     * @param keyword     搜索关键字
     * @return 分页数据 {@link PaginationData}
     */
    PaginationData<KindleBook> pageBooks(int requestPage, int pageSize, String keyword);

}
