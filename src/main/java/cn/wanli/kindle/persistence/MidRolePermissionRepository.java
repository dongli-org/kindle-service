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

import cn.wanli.kindle.domain.MidRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-06 22:55
 */
public interface MidRolePermissionRepository extends JpaRepository<MidRolePermission, Long> {
    /**
     * 通过rid, pid和是否被删除查找角色和权限之间的关系
     *
     * @param rid     Role id
     * @param pid     Permission id
     * @param deleted 是否被删除
     * @return {@link MidRolePermission}
     */
    Optional<MidRolePermission> findByRoleIdAndPermissionIdAndDeleted(Long rid, Long pid, Boolean deleted);
}
