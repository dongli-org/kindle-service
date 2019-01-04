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

import cn.wanli.kindle.domain.Group;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-04 01:01
 */
public interface GroupService {
    /**
     * 保存 group
     *
     * @param group see {@link Group}
     * @return 保存的Group
     */
    Group save(Group group);

    /**
     * 通过Group id查找Group
     *
     * @param id group id
     * @return {@link Group} group
     */
    Optional<Group> findById(Long id);
}
