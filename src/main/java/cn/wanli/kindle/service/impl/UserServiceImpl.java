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

import cn.wanli.kindle.domain.*;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.PasswordEntity;
import cn.wanli.kindle.entity.UserSimpleEntity;
import cn.wanli.kindle.entity.UserEntity;
import cn.wanli.kindle.persistence.UserRepository;
import cn.wanli.kindle.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
    public PaginationData<UserEntity> pageUsers(int requestPage, int pageSize, String keyword) {
        PaginationData<UserEntity> data = new PaginationData<>();
        Page<User> page;
        if (Strings.isBlank(keyword)) {
            page = userRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = userRepository.findAllByNameContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        data.setTotalSize(page.getTotalElements());
        data.setPageSize(page.getSize());
        data.setCurrentPage(requestPage);
        data.setData(page.getContent().stream().map(this::user2Entity).collect(toList()));
        return data;
    }

    /**
     * 将持久化用户转化为 entity
     *
     * @param user 持久化user
     * @return {@link UserEntity}
     */
    private UserEntity user2Entity(User user) {
        UserEntity entity = new UserEntity(user.getId(), user.getName(), user.getEmail());
        entity.setRoles(user.getMidUserRoles().stream().map(MidUserRole::getRole).map(Role::getName).collect(toList()));
        entity.setPermissions(user.getMidUserRoles().stream()
                .map(MidUserRole::getRole)
                .map(Role::getMidRolePermissions)
                .map(midRolePermissions -> midRolePermissions.stream().map(MidRolePermission::getPermission))
                .flatMap(permissionStream -> permissionStream.map(Permission::getName)).distinct().collect(toList()));
        return entity;
    }

    @Override
    public Optional<User> findById(Long uid) {
        return userRepository.findById(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerUser(UserSimpleEntity entity) {
        User user = new User(entity.getName(), passwordEncoder.encode(entity.getPassword()), entity.getEmail());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyAccount(Long id, UserSimpleEntity entity) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEmail(entity.getEmail());
            user.setName(entity.getName());
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
