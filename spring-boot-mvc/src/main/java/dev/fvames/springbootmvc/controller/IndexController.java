package dev.fvames.springbootmvc.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @RequestMapping("/indexBoot")
    public String index() {
        System.out.println("indexBoot");
        return "index";
    }

    @RequestMapping("/notPageFound")
    public void notPageFound() {
        throw new NullPointerException("抛出空指针异常");
    }

}
