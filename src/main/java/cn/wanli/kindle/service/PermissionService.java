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

import cn.wanli.kindle.domain.Permission;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.PermissionEntity;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-06 00:04
 */
public interface PermissionService {
    /**
     * 分页获取用户权限
     *
     * @param requestPage 请求页数
     * @param pageSize    每页数量
     * @param keyword     关键字
     * @return {@link PaginationData} 分页容器 {@link PermissionEntity}
     */
    PaginationData<PermissionEntity> pagePermissions(int requestPage, int pageSize, String keyword);

    /**
     * 获取指定用户权限
     *
     * @param pid permission id
     * @return {@link PermissionEntity}
     */
    PermissionEntity getPerEntity(Long pid);

    /**
     * 根据Permission Name的名字获取用户权限
     *
     * @param permissionName permission name
     * @return permission
     */
    Optional<Permission> getPermission(String permissionName);

    /**
     * 添加用户权限
     *
     * @param entity 用户权限信息
     * @return {@link PermissionEntity}
     */
    PermissionEntity addPermission(PermissionEntity entity);

    /**
     * 修改指定用户权限
     *
     * @param pid    用户权限id
     * @param entity 需要修改的角色权限信息
     * @return 返回修改的角色权限信息
     */
    PermissionEntity modifyPermission(Long pid, PermissionEntity entity);
}
