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
import cn.wanli.kindle.entity.GroupEntity;
import cn.wanli.kindle.persistence.GroupRepository;
import cn.wanli.kindle.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
            Group group = new Group();
            group.setName(entity.getName());
            group.setDesc(entity.getDesc());
            Group save = groupRepository.save(group);
            entity.setId(save.getId());
            return entity;
        }
    }

}
