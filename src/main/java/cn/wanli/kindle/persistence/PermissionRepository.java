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

package cn.wanli.kindle.persistence;

import cn.wanli.kindle.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-06 00:07
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    /**
     * 分页模糊查找包含关键字用户权限
     *
     * @param keyword  关键字
     * @param pageable 分页信息
     * @return {@link Permission} 用户权限
     */
    Page<Permission> findAllByNameContaining(String keyword, Pageable pageable);

    /**
     * 根据permission name获取permission
     *
     * @param name permission name
     * @return {@link Optional} permission
     */
    Optional<Permission> findByName(String name);
}
