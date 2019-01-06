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

import cn.wanli.kindle.domain.Permission;
import cn.wanli.kindle.domain.Role;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.RoleEntity;
import cn.wanli.kindle.entity.RoleUserEntity;
import cn.wanli.kindle.entity.UserEntity;
import cn.wanli.kindle.persistence.RoleRepository;
import cn.wanli.kindle.service.RoleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author wanli
 * @date 2018-12-06 22:48
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleEntity addRole(RoleEntity entity) {
        Optional<Role> roleOptional = roleRepository.findByName(entity.getName());
        if (roleOptional.isPresent()) {
            return null;
        } else {
            Role role = new Role(entity.getName(), entity.getDesc());
            Role save = roleRepository.save(role);
            entity.setId(save.getId());
            return entity;
        }
    }

    @Override
    public PaginationData<RoleEntity> paginationRoles(int requestPage, int pageSize, String keyword) {
        PaginationData<RoleEntity> data = new PaginationData<>();
        Page<Role> page;
        if (Strings.isBlank(keyword)) {
            page = roleRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = roleRepository.findAllByNameContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getSize());
        data.setTotalSize(page.getTotalElements());
        data.setData(page.getContent().stream().map(p -> new RoleEntity(p.getId(), p.getName(), p.getDesc())).collect(toList()));
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleUserEntity getRole(Long rid) {
        return roleRepository.findById(rid).map(role -> {
            RoleUserEntity entity = new RoleUserEntity(role.getId(), role.getName(), role.getDesc());
            entity.setUsers(role.getUsers().stream().map(user -> {
                UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getEmail());
                userEntity.setRoles(user.getRoles().stream().map(Role::getName).collect(toList()));
                userEntity.setPermissions(user.getRoles().stream()
                        .flatMap(g -> g.getPermissions().stream().map(Permission::getName)).distinct().collect(toList()));
                return userEntity;
            }).collect(toList()));
            return entity;
        }).orElse(new RoleUserEntity());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleEntity modifyRole(Long rid, RoleEntity entity) {
        return roleRepository.findById(rid).map(role -> {
            role.setName(entity.getName());
            role.setDesc(entity.getDesc());
            entity.setId(role.getId());
            return entity;
        }).orElse(entity);
    }

}
