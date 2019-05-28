package dev.fvames.springJunit.controller;

import dev.fvames.springJunit.domain.User;
import dev.fvames.springJunit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 2019/5/28 11:09
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    public List<User> findAll() {
        return userService.findAll();
    }

}
