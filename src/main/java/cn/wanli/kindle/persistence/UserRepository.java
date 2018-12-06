package cn.wanli.kindle.persistence;

import cn.wanli.kindle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wanli
 * @date 2018-12-06 20:34
 */
public interface UserRepository extends JpaRepository<User, String> {

}
