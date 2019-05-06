package dev.frames.demo.controller;

import dev.frames.demo.dao.User;
import dev.frames.demo.service.UserService;
import dev.frames.spring.annotation.Autowired;
import dev.frames.spring.annotation.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public User getUser(String userName) {
        User user = userService.getUserByName(userName);
        return user;
    }
}
