package cn.wanli.kindle.persistence;

import cn.wanli.kindle.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wanli
 * @date 2018-12-06 22:47
 */
public interface GroupRepository extends JpaRepository<Group, String> {
}
