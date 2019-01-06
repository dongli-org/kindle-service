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

import cn.wanli.kindle.domain.Group;
import cn.wanli.kindle.domain.Permission;
import cn.wanli.kindle.entity.GroupEntity;
import cn.wanli.kindle.entity.GroupUserEntity;
import cn.wanli.kindle.entity.PaginationData;
import cn.wanli.kindle.entity.UserEntity;
import cn.wanli.kindle.persistence.GroupRepository;
import cn.wanli.kindle.service.GroupService;
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
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GroupEntity addGroup(GroupEntity entity) {
        Optional<Group> groupOptional = groupRepository.findByName(entity.getName());
        if (groupOptional.isPresent()) {
            return null;
        } else {
            Group group = new Group(entity.getName(), entity.getDesc());
            Group save = groupRepository.save(group);
            entity.setId(save.getId());
            return entity;
        }
    }

    @Override
    public PaginationData<GroupEntity> paginationGroups(int requestPage, int pageSize, String keyword) {
        PaginationData<GroupEntity> data = new PaginationData<>();
        Page<Group> page;
        if (Strings.isBlank(keyword)) {
            page = groupRepository.findAll(PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        } else {
            page = groupRepository.findAllByNameContaining(keyword,
                    PageRequest.of(requestPage - 1, pageSize, Sort.by("id").ascending()));
        }
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getSize());
        data.setTotalSize(page.getTotalElements());
        data.setData(page.getContent().stream().map(p -> new GroupEntity(p.getId(), p.getName(), p.getDesc())).collect(toList()));
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GroupUserEntity getGroup(Long gid) {
        return groupRepository.findById(gid).map(group -> {
            GroupUserEntity entity = new GroupUserEntity(group.getId(), group.getName(), group.getDesc());
            entity.setUsers(group.getUsers().stream().map(user -> {
                UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getEmail());
                userEntity.setGroups(user.getGroups().stream().map(Group::getName).collect(toList()));
                userEntity.setPermissions(user.getGroups().stream()
                        .flatMap(g -> g.getPermissions().stream().map(Permission::getName)).distinct().collect(toList()));
                return userEntity;
            }).collect(toList()));
            return entity;
        }).orElse(new GroupUserEntity());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GroupEntity modifyGroup(Long gid, GroupEntity groupEntity) {
        return getGroupEntity(gid, groupEntity, groupRepository);
    }

    public static GroupEntity getGroupEntity(Long gid, GroupEntity groupEntity, GroupRepository groupRepository) {
        return groupRepository.findById(gid).map(group -> {
            group.setName(groupEntity.getName());
            group.setDesc(groupEntity.getDesc());
            groupEntity.setId(group.getId());
            return groupEntity;
        }).orElse(groupEntity);
    }

}
