package dev.fvames.springjunit.service;

import dev.fvames.springJunit.domain.User;
import dev.fvames.springJunit.service.IUserService;
import dev.fvames.springJunit.service.impl.InMemoryUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 使用 junit 5 测试
 *
 * @version 2019/5/27 17:59
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = InMemoryUserServiceImpl.class)
@SpringJUnitConfig(classes = InMemoryUserServiceImpl.class)
public class UserServiceTestInJunit5 {

    @Autowired
    private IUserService userService;

    /**
     * 測試 {@link IUserService#save(User)}
     */
    @Test
    public void testSave() {
        User user = new User();

        user.setId("1");
        user.setName("平安");

        assertTrue(userService.save(user));
        assertFalse(userService.save(user));
    }
}
