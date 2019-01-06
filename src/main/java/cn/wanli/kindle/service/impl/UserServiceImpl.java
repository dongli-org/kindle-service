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

package cn.wanli.kindle.service.impl;

import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.entity.PasswordEntity;
import cn.wanli.kindle.entity.UserDTO;
import cn.wanli.kindle.persistence.UserRepository;
import cn.wanli.kindle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author wanli
 * @date 2018-12-06 20:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long uid) {
        return userRepository.findById(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerUser(UserDTO dto) {
        User user = new User(dto.getName(), passwordEncoder.encode(dto.getPassword()), dto.getEmail());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyAccount(Long id, UserDTO dto) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyNickname(Long id, String nickname) {
        userRepository.findById(id).ifPresent(user -> user.setNickname(nickname));
    }

    @Override
    public boolean modifyPassword(Long id, PasswordEntity entity) {
        return userRepository.findById(id).map(user -> {
            if (passwordEncoder.matches(entity.getOriginal(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(entity.getPresent()));
                return true;
            }
            return false;
        }).orElse(false);
    }
}
