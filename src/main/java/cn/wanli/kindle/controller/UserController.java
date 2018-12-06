package cn.wanli.kindle.controller;

import cn.wanli.kindle.domain.User;
import cn.wanli.kindle.dto.UserDTO;
import cn.wanli.kindle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanli
 * @date 2018-12-06 22:52
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> users() {
        List<User> all = userService.findAll();
        return all.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<User> addUser(UserDTO dto) {
        User user = userService.registerUser(dto);
        return user == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(user);
    }


}
