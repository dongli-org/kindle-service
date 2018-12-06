package cn.wanli.kindle.service;

import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.dto.UserDTO;
import cn.wanli.kindle.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.wanli.kindle.utils.CommonsUtils.md5Encrypt;
import static cn.wanli.kindle.utils.CommonsUtils.primaryKey;

/**
 * @author wanli
 * @date 2018-12-06 20:36
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User registerUser(UserDTO dto) {
        User user = new User(primaryKey(), dto.getName(), md5Encrypt(dto.getPassword()), dto.getEmail());
        user.setAvailable(true);
        return userRepository.save(user);
    }
}
