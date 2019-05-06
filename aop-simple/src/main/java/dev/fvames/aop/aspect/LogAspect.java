package dev.fvames.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.core.Ordered;

public class LogAspect implements Ordered {

    private final static Logger LOG = Logger.getLogger(LogAspect.class);

    public void before(JoinPoint joinPoint){
        LOG.info("调用方法之前执行" + joinPoint);
    }

    public void after(JoinPoint joinPoint){
        LOG.info("调用之后执行" + joinPoint);
    }

    public boolean afterReturn(JoinPoint joinPoint){
        LOG.info("调用获得返回值之后执行" + joinPoint);
        return true;
    }


    public void afterThrow(JoinPoint joinPoint){
        LOG.info("抛出异常之后执行" + joinPoint);
    }

    public int getOrder() {
        return 0;
    }
}
