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

import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.dto.UserDTO;
import cn.wanli.kindle.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.wanli.kindle.utils.CommonsUtils.md5Encrypt;
import static cn.wanli.kindle.utils.CommonsUtils.primaryKey;

/**
 * @author wanli
 * @date 2018-12-06 20:36
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User registerUser(UserDTO dto) {
        User user = new User(primaryKey(), dto.getName(), md5Encrypt(dto.getPassword()), dto.getEmail());
        user.setAvailable(true);
        return userRepository.save(user);
    }
}
