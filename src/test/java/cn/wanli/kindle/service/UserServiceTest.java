package cn.wanli.kindle.service;

import cn.wanli.kindle.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static cn.wanli.kindle.utils.CommonsUtils.primaryKey;

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
        UserDTO user = new UserDTO();
        user.setId(primaryKey());
        user.setName("wanli");
        user.setPassword("123456");
        user.setEmail("wanlinus@qq.com");
        userService.registerUser(user);
    }
    
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void findAll() {
        userService.findAll().forEach(System.out::println);
    }
}