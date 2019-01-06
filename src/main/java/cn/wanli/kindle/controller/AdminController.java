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

import cn.wanli.kindle.entity.PermissionEntity;
import cn.wanli.kindle.entity.RoleEntity;
import cn.wanli.kindle.service.PermissionService;
import cn.wanli.kindle.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanli
 * @date 2019-01-04 10:57
 */
@RestController
@RequestMapping("/v1/admin")
@Api("管理员相关操作")
public class AdminController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/roles/{rid}")
    @ApiOperation("获取指定的用户角色")
    public ResponseEntity getRole(@PathVariable Long gid) {
        return ResponseEntity.ok(roleService.getRole(gid));
    }

    @GetMapping("/roles")
    @ApiOperation("分页获取用户角色")
    public ResponseEntity pageRoles(@RequestParam(defaultValue = "1") int requestPage,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam String keyword) {
        return ResponseEntity.ok(roleService.paginationRoles(requestPage, pageSize, keyword));
    }

    @PostMapping("/roles")
    @ApiOperation("添加用户角色")
    public ResponseEntity addRole(@RequestBody RoleEntity entity) {
        return ResponseEntity.ok(roleService.addRole(entity));
    }

    @PutMapping("/roles/{rid}")
    @ApiOperation("修改指定用户组")
    public ResponseEntity modifyRole(@PathVariable Long rid, @RequestBody RoleEntity roleEntity) {
        return ResponseEntity.ok(roleService.modifyRole(rid, roleEntity));
    }

    @GetMapping("/permissions")
    @ApiOperation("分页获取用户权限")
    public ResponseEntity pagePermissions(@RequestParam(defaultValue = "1") int requestPage,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          @RequestParam String keyword) {
        return ResponseEntity.ok(permissionService.pagePermissions(requestPage, pageSize, keyword));
    }

    @GetMapping("/permissions/{pid}")
    @ApiOperation("获取指定权限信息")
    public ResponseEntity getPermission(@PathVariable Long pid) {
        return ResponseEntity.ok(permissionService.getPerEntity(pid));
    }

    @PostMapping("/permissions")
    @ApiOperation("新增用户权限")
    public ResponseEntity addPermission(@RequestBody PermissionEntity entity) {
        return ResponseEntity.ok(permissionService.addPermission(entity));
    }

    @PutMapping("/permissions/{pid}")
    @ApiOperation("修改指定用户权限")
    public ResponseEntity modifyPermission(@PathVariable Long pid, @RequestBody PermissionEntity entity) {
        return ResponseEntity.ok(permissionService.modifyPermission(pid, entity));
    }
}
