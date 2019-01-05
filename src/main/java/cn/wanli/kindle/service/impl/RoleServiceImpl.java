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

import cn.wanli.kindle.domain.Role;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.RoleEntity;
import cn.wanli.kindle.persistence.RoleRepository;
import cn.wanli.kindle.service.RoleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author wanli
 * @date 2019-01-06 00:04
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PaginationData<RoleEntity> pageRoles(int requestPage, int pageSize, String keyword) {
        PaginationData<RoleEntity> data;
        Page<Role> page;
        if (Strings.isBlank(keyword)) {
            page = roleRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = roleRepository.findAllByNameContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        List<RoleEntity> entityList = page.getContent().stream()
                .map(r -> new RoleEntity(r.getId(), r.getName(), r.getDesc()))
                .collect(toList());
        data = new PaginationData<>(page.getNumber(), page.getSize(), page.getTotalElements(), entityList);
        return data;
    }
}
