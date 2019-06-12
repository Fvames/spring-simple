package dev.fvames.springbootjdbc.controller;

import dev.fvames.springbootjdbc.domain.UserInfo;
import dev.fvames.springbootjdbc.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 2019/6/11 17:57
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/web/mvc/user/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean save(@RequestBody UserInfo userInfo) {
        System.out.println(">>>> save");
        //UserInfo user = new UserInfo();
        //user.setUserName(userName);

        return userRepository.jdbcSave(userInfo);
        //return userRepository.transactionalSave(user);
        //return userRepository.save(user);
    }
}
