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

import cn.wanli.kindle.entity.UserDTO;
import cn.wanli.kindle.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanli
 * @date 2018-12-06 20:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;


    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(false)
    public void saveUser() {
        UserDTO user = new UserDTO();
        user.setName("wanli");
        user.setPassword("123456");
        user.setEmail("wanlinus@qq.com");
        userService.registerUser(user);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void findAll() {
        userService.findAll().forEach(System.out::println);
    }
}