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

import cn.wanli.kindle.config.KindleConstant;
import cn.wanli.kindle.config.security.JwtTokenUtil;
import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.entity.AuthorizationUser;
import cn.wanli.kindle.entity.PasswordEntity;
import cn.wanli.kindle.entity.UserDTO;
import cn.wanli.kindle.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static cn.wanli.kindle.config.KindleConstant.BEARER;

/**
 * @author wanli
 * @date 2018-12-06 22:52
 */
@RestController
@RequestMapping("/v1/users")
@Api("Operations about users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil util;

    @Autowired
    public UserController(UserServiceImpl userService, @Qualifier("userDetail") UserDetailsService userDetailsService,
                          BCryptPasswordEncoder passwordEncoder, JwtTokenUtil util) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.util = util;
    }

    @GetMapping
    @ApiOperation(value = "获取所有用户", response = ResponseEntity.class)
    public ResponseEntity users(@RequestParam(defaultValue = "1") int requestPage,
                                @RequestParam(defaultValue = "10") int pageSize,
                                @RequestParam String keyword) {
        return ResponseEntity.ok(userService.pageUsers(requestPage, pageSize, keyword));
    }

    @PostMapping
    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "dto", value = "注册用户的基本信息", dataTypeClass = UserDTO.class)
    public ResponseEntity<User> registerUser(UserDTO dto) {
        User user = userService.registerUser(dto);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    @ApiImplicitParam(name = "user", value = "用户登陆认证信息", dataTypeClass = AuthorizationUser.class)
    public ResponseEntity login(@RequestBody AuthorizationUser user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码或用户名错误");
        }
        if (!userDetails.isEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账户被锁定");
        }
        return ResponseEntity.ok(BEARER + util.generateToken(userDetails));
    }

    @PutMapping
    @ApiOperation("修改账号信息")
    @ApiImplicitParam(name = "dto", value = "用户修改信息", dataTypeClass = UserDTO.class)
    public ResponseEntity modifyAccount(@RequestBody UserDTO dto, HttpServletRequest request) {
        String authHeader = request.getHeader(KindleConstant.AUTHORIZATION);
        Long id = util.getUserIdFromToken(authHeader.substring(7));
        userService.modifyAccount(id, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("nickname")
    @ApiOperation("修改用户昵称")
    @ApiImplicitParam(name = "nickname")
    public ResponseEntity modifyNickname(String nickname, HttpServletRequest request) {
        String authHeader = request.getHeader(KindleConstant.AUTHORIZATION);
        Long id = util.getUserIdFromToken(authHeader.substring(7));
        userService.modifyNickname(id, nickname);
        return ResponseEntity.ok().build();
    }

    @PutMapping("password")
    @ApiOperation("修改密码")
    @ApiImplicitParam(name = "entity", value = "新旧密码", dataTypeClass = PasswordEntity.class)
    public ResponseEntity<Boolean> modifyPassword(@RequestBody PasswordEntity entity, HttpServletRequest request) {
        String authHeader = request.getHeader(KindleConstant.AUTHORIZATION);
        Long id = util.getUserIdFromToken(authHeader.substring(7));
        return ResponseEntity.ok(userService.modifyPassword(id, entity));
    }


}
