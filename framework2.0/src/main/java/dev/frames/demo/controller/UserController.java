package dev.frames.demo.controller;

import dev.frames.demo.dao.User;
import dev.frames.demo.service.UserService;
import dev.frames.spring.annotation.Autowired;
import dev.frames.spring.annotation.Controller;
import dev.frames.spring.annotation.RequestMapping;
import dev.frames.spring.annotation.RequestParam;
import dev.frames.spring.web.servlet.V2ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired(value = "userService")
    private UserService userService;

    @RequestMapping("/get")
    public V2ModelAndView getUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("userName") String userName) {
        User user = userService.getUserByName(userName);

        System.out.println(">>>>>>> getUser method .......");
        Map<String, String> map = new HashMap<>();
        map.put("userName", user.getUserName());
        return new V2ModelAndView("/user.html", map);
    }
}
