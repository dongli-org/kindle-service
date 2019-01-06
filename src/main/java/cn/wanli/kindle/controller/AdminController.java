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

import cn.wanli.kindle.entity.GroupEntity;
import cn.wanli.kindle.entity.PermissionEntity;
import cn.wanli.kindle.service.GroupService;
import cn.wanli.kindle.service.PermissionService;
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
    private GroupService groupService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/groups/{gid}")
    @ApiOperation("获取指定的用户组")
    public ResponseEntity getGroup(@PathVariable Long gid) {
        return ResponseEntity.ok(groupService.getGroup(gid));
    }

    @GetMapping("/groups")
    @ApiOperation("分页获取用户组")
    public ResponseEntity pageGroups(@RequestParam(defaultValue = "1") int requestPage,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam String keyword) {
        return ResponseEntity.ok(groupService.paginationGroups(requestPage, pageSize, keyword));
    }

    @PostMapping("/groups")
    @ApiOperation("添加用户组")
    public ResponseEntity addGroup(@RequestBody GroupEntity group) {
        return ResponseEntity.ok(groupService.addGroup(group));
    }

    @PutMapping("/groups/{gid}")
    @ApiOperation("修改指定用户组")
    public ResponseEntity modifyGroup(@PathVariable Long gid, @RequestBody GroupEntity groupEntity) {
        return ResponseEntity.ok(groupService.modifyGroup(gid, groupEntity));
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
