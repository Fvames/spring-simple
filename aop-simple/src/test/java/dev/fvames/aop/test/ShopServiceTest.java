package dev.fvames.aop.test;

import dev.fvames.aop.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:application-context.xml")
public class ShopServiceTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void before() {
        shopService.buyApple("iPad", 2, 3000.0);
    }
}
