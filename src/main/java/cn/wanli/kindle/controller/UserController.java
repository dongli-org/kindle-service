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

import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.dto.UserDTO;
import cn.wanli.kindle.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 22:52
 */
@RestController
@RequestMapping("/v1/users")
@Api("Operations about users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "获取所有用户", response = ResponseEntity.class)
    public ResponseEntity<List<User>> users() {
        List<User> all = userService.findAll();
        return all.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(all);
    }

    @PostMapping
    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "dto", value = "注册用户的基本信息", dataTypeClass = UserDTO.class)
    public ResponseEntity<User> registerUser(UserDTO dto) {
        User user = userService.registerUser(dto);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }


}
