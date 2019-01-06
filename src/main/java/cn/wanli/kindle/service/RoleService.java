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

import cn.wanli.kindle.domain.Role;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.RoleEntity;
import cn.wanli.kindle.entity.RoleUserEntity;

import java.util.Optional;

/**
 * @author wanli
 * @date 2019-01-04 01:01
 */
public interface RoleService {
    /**
     * 保存角色
     *
     * @param role see {@link Role}
     * @return 保存的用户角色
     */
    Role save(Role role);

    /**
     * 通过Role id查找Role
     *
     * @param id role id
     * @return {@link Role} role
     */
    Optional<Role> findById(Long id);

    /**
     * 添加用户角色
     *
     * @param role role
     * @return 添加成功返回true, 否则返回false
     */
    RoleEntity addRole(RoleEntity role);

    /**
     * 分页获取所有用户角色
     *
     * @param requestPage 请求页
     * @param pageSize    请求
     * @param keyword     关键字
     * @return {@link PaginationData} 通用分页数据
     */
    PaginationData<RoleEntity> paginationRoles(int requestPage, int pageSize, String keyword);

    /**
     * 根据id获取指定用户角色
     *
     * @param rid 角色id
     * @return {@link RoleUserEntity} 包含用户信息的角色数据
     */
    RoleUserEntity getRole(Long rid);

    /**
     * 修改指定角色的信息
     *
     * @param rid    role id
     * @param entity {@link RoleEntity}
     * @return 修改后的role信息
     */
    RoleEntity modifyRole(Long rid, RoleEntity entity);

    /**
     * 为用户添加角色
     *
     * @param rid 角色id
     * @param uid 用户id
     * @return 修改成功true 否则false
     */
    Boolean addUser2Role(Long rid, Long uid);

    /**
     * 移除用户角色
     *
     * @param rid 角色ID
     * @param uid 用户ID
     * @return 移除用户角色成功true 失败false
     */
    Boolean delUserInRole(Long rid, Long uid);

    /**
     * 为用户角色分配权限
     *
     * @param rid 角色id
     * @param pid 权限id
     * @return 分配成功返回true, 失败false
     */
    Boolean addPer2Role(Long rid, Long pid);

    /**
     * 移除角色权限
     *
     * @param rid 角色id
     * @param pid 权限id
     * @return 移除成功返回true 否则false
     */
    Boolean delPerInRole(Long rid, Long pid);
}
