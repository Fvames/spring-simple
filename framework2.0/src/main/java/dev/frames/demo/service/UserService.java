package dev.frames.demo.service;

import dev.frames.demo.dao.User;
import dev.frames.spring.annotation.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("艾丽娅", new User("艾丽娅", 24, "女"));
        userMap.put("琼恩", new User("琼恩", 30, "男"));
    }

    public User getUserByName(String userName) {
        return userMap.get(userName);
    }

}
