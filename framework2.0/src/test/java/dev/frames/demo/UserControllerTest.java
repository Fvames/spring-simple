package dev.frames.demo;

import dev.frames.demo.controller.UserController;
import dev.frames.demo.dao.User;
import dev.frames.spring.context.V2ApplicationContext;
import org.junit.Test;

public class UserControllerTest {

    @Test
    public void testGetUser() {
        V2ApplicationContext applicationContext = new V2ApplicationContext("classpath:application.properties");

        UserController userController = (UserController) applicationContext.getBean("userController");

        User user = userController.getUser("琼恩");
        System.out.println("user: " + user);

    }

}
