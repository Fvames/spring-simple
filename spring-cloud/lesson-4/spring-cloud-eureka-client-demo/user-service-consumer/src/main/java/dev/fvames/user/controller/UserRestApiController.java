package dev.fvames.user.controller;

import dev.fvames.user.domain.User;
import dev.fvames.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * {@link UserService 用户服务} REST API
 */
@RestController
public class UserRestApiController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     * @param name
     * @return 成功返回 {@link User},否则返回 <code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);

        if (userService.save(user)) {
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
