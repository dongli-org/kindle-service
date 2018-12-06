package cn.wanli.kindle.service;

import cn.wanli.kindle.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanli
 * @date 2018-12-06 20:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(false)
    public void saveUser() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("wanli");
        user.setPassword("123456");
        user.setEmail("wanlinus@qq.com");
        user.setAvailable(false);
        userService.saveUser(user);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void findUser() {
        userService.findUser(1L).ifPresent(System.out::println);

    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void findAll() {
        userService.findAll().forEach(System.out::println);
    }
}