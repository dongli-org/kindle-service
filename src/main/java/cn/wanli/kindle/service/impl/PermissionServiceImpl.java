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
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.PermissionEntity;
import cn.wanli.kindle.persistence.PermissionRepository;
import cn.wanli.kindle.service.PermissionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author wanli
 * @date 2019-01-06 00:04
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final String ID = "id";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PaginationData<PermissionEntity> pagePermissions(int requestPage, int pageSize, String keyword) {
        PaginationData<PermissionEntity> data;
        Page<Permission> page;
        if (Strings.isBlank(keyword)) {
            page = permissionRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by(ID).ascending()));
        } else {
            page = permissionRepository.findAllByNameContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by(ID).ascending()));
        }
        List<PermissionEntity> entityList = page.getContent().stream()
                .map(r -> new PermissionEntity(r.getId(), r.getName(), r.getDesc()))
                .collect(toList());
        data = new PaginationData<>(page.getNumber(), page.getSize(), page.getTotalElements(), entityList);
        return data;
    }

    @Override
    public PermissionEntity getPerEntity(Long rid) {
        return permissionRepository.findById(rid)
                .map(permission -> new PermissionEntity(permission.getId(), permission.getName(), permission.getDesc()))
                .orElse(new PermissionEntity());
    }

    @Override
    public Optional<Permission> getPermission(String permissionName) {
        return permissionRepository.findByName(permissionName);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionEntity addPermission(PermissionEntity entity) {
        Optional<Permission> perOpt = permissionRepository.findByName(entity.getName());
        if (perOpt.isPresent()) {
            return null;
        }
        Permission permission = new Permission(entity.getName(), entity.getDesc());
        Permission save = permissionRepository.save(permission);
        entity.setId(save.getId());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionEntity modifyPermission(Long pid, PermissionEntity entity) {
        return permissionRepository.findById(pid).map(permission -> {
            permission.setName(entity.getName());
            permission.setDesc(entity.getDesc());
            entity.setId(permission.getId());
            return entity;
        }).orElse(entity);
    }
}
