package dev.frames.demo.controller;

import dev.frames.demo.dao.User;
import dev.frames.demo.service.UserService;
import dev.frames.spring.annotation.Autowired;
import dev.frames.spring.annotation.Controller;

@Controller
public class UserController {
    // todo 必须写入 value 值，缓存的 key 或者查找注入的 key 可能为 UserServie，待排查处理
    @Autowired(value = "userService")
    private UserService userService;

    public User getUser(String userName) {
        User user = userService.getUserByName(userName);
        return user;
    }
}
