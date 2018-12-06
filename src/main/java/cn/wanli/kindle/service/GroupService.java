package cn.wanli.kindle.service;

import cn.wanli.kindle.domain.Group;
import cn.wanli.kindle.persistence.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wanli
 * @date 2018-12-06 22:48
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> findUser(Long id) {
        return groupRepository.findById(id);
    }

}
