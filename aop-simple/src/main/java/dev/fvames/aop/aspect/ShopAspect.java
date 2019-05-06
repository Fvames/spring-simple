package dev.fvames.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ShopAspect implements Ordered{
    Logger lg = Logger.getLogger(ShopAspect.class);

    @Pointcut(value = "execution(* dev.fvames.aop.service..*(..))")
    public void pointCut() { }

    @Before("pointCut()")
    //@Before(value = "dev.fvames.aop.service.ShopService.buyApple() && target(productName)")
    public void before(JoinPoint joinPoint) {
        lg.info(">>> 调用方法开始之前    ");
    }

    public int getOrder() {
        return 1;
    }
}
