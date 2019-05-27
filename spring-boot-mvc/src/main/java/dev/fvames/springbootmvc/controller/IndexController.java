package dev.fvames.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/jsp")
    public String jsp() {
        System.out.println(">>> jsp");
        return "hello-jsp";
    }

    @RequestMapping("/freemarker")
    public String freemarker() {
        System.out.println(">>> freemarker");
        return "freemarker";
    }

    @RequestMapping("/freemarker1")
    public String freemarker1() {
        System.out.println(">>> freemarker1");
        return "freemarker1";
    }

    @RequestMapping("/thymeleaf")
    public String index() {
        System.out.println(">>> thymeleaf");
        return "index";
    }

    @RequestMapping("/notPageFound")
    public void notPageFound() {
        throw new NullPointerException("抛出空指针异常");
    }

}
