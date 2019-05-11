package dev.frames.demo.controller;

import dev.frames.demo.dao.User;
import dev.frames.demo.service.UserService;
import dev.frames.spring.annotation.Autowired;
import dev.frames.spring.annotation.Controller;
import dev.frames.spring.annotation.RequestMapping;
import dev.frames.spring.annotation.RequestParam;
import dev.frames.spring.web.servlet.V2ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public V2ModelAndView getUser(@RequestParam("userName") String userName) {
        User user = userService.getUserByName(userName);

        Map<String, String> map = new HashMap<>();
        map.put("userName", user.getUserName());

        return new V2ModelAndView("user.html", map);
    }
}
