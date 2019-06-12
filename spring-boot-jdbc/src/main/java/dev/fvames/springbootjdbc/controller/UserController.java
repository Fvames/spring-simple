package dev.fvames.springbootjdbc.controller;

import dev.fvames.springbootjdbc.domain.UserInfo;
import dev.fvames.springbootjdbc.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/user/save")
    public Boolean save(String userName) {
        System.out.println(">>>> save");
        UserInfo user = new UserInfo();
        user.setUserName(userName);

        return userRepository.jdbcSave(user);
        //return userRepository.transactionalSave(user);
        //return userRepository.save(user);
    }
}
