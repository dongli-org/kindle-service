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
import cn.wanli.kindle.service.GroupService;
import cn.wanli.kindle.service.RoleService;
import io.swagger.annotations.Api;
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
    private RoleService roleService;

    @GetMapping("/groups/{gid}")
    public ResponseEntity getGroup(@PathVariable Long gid) {
        return ResponseEntity.ok(groupService.getGroup(gid));
    }

    @GetMapping("/groups")
    public ResponseEntity pageGroups(@RequestParam(defaultValue = "1") int requestPage,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam String keyword) {
        return ResponseEntity.ok(groupService.paginationGroups(requestPage, pageSize, keyword));
    }

    @PostMapping("/groups")
    public ResponseEntity addGroup(@RequestBody GroupEntity group) {
        return ResponseEntity.ok(groupService.addGroup(group));
    }

    @PutMapping("/groups/{gid}")
    public ResponseEntity modifyGroup(@PathVariable Long gid, @RequestBody GroupEntity groupEntity) {
        return ResponseEntity.ok(groupService.modifyGroup(gid, groupEntity));
    }

    @GetMapping("/roles")
    public ResponseEntity pageRoles(@RequestParam(defaultValue = "1") int requestPage,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam String keyword) {
        return ResponseEntity.ok(roleService.pageRoles(requestPage, pageSize, keyword));

    }
}
