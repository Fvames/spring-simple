package dev.fvames.springjunit.controller;

import dev.fvames.springJunit.controller.UserController;
import dev.fvames.springJunit.domain.User;
import dev.fvames.springJunit.service.impl.UserRemoteServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 2019/5/28 11:26
 */
@SpringJUnitConfig(classes = {UserController.class, UserControllerTest.TestConfiguration.class})
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testFindAll() {
        // controller -> mock remoteService
        List<User> users = userController.findAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals("张三", users.get(0).getName());
    }

    @Configuration
    static class TestConfiguration {

        @Bean
        public UserRemoteServiceImpl userRemoteService() {
            UserRemoteServiceImpl remoteService = mock(UserRemoteServiceImpl.class);
            User user = new User();
            user.setId("1");
            user.setName("张三");

            when(remoteService.findAll()).thenReturn(Arrays.asList(user));

            return remoteService;
        }
    }
}
