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

package cn.wanli.kindle.init;

import cn.wanli.kindle.domain.Role;
import cn.wanli.kindle.domain.Permission;
import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.persistence.RoleRepository;
import cn.wanli.kindle.persistence.PermissionRepository;
import cn.wanli.kindle.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


/**
 * @author wanli
 * @date 2019-01-06 16:42
 */
@Component
@Profile("dev")
public class InitDatabases implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDatabases.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) throws Exception {
        this.initUser();
        this.initRoles();
    }


    private void initUser() {
        if (userRepository.findAll().isEmpty()) {
            User user = new User("wanli", passwordEncoder.encode("123456"), "wanlinus@qq.com");
            user.setAccountNonExpired(true);
            user.setEnabled(true);
            user.setSystemPush(true);
            userRepository.save(user);
            LOGGER.info("----- 初始化用户表完成-----");
        }
    }

    private void initRoles() {
        if (roleRepository.findAll().isEmpty()) {
            Role role = new Role("admin", "admin");
            userRepository.findByName("wanli").ifPresent(user -> role.setUsers(Collections.singletonList(user)));
            roleRepository.save(role);
            LOGGER.info("----- 初始化用户组完成 -----");
        }
    }

    private void initPermission() {
        if (permissionRepository.findAll().isEmpty()) {
            Permission permission = new Permission();
        }
    }
}
