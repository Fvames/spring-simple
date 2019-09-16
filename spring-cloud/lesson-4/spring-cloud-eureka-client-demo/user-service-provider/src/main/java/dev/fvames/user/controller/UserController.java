package dev.fvames.user.controller;

import dev.fvames.user.domain.User;
import dev.fvames.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * {@link UserService 用户服务} 提供方 REST API {@link RestController}
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     * @param user
     * @return 成功返回 {@link User},否则返回 <code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {

        if (userService.save(user)) {
            System.out.println("UserService 服务方：保存用户成功。" + user);
            return user;
        } else {
            return null;
        }
    }

    /**
     * 返回所有的用户数据
     * @return
     */
    @GetMapping("/user/list")
    public Collection<User> list() {
        return userService.findAll();
    }
}
