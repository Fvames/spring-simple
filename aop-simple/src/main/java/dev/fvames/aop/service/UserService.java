package dev.fvames.aop.service;

import dev.fvames.aop.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final static Logger LOG = Logger.getLogger(UserService.class);

    public User login(String loginName,String loginPass){
        LOG.info("用户登录");
        return null;
    }
    public User login(int name,String loginPass){
        LOG.info("用户登录");
        return null;
    }
    public User login(String name, int loginPass){
        LOG.info("用户登录");
        return null;
    }

    public boolean logout(String loginName){
        LOG.info("注销登录");
        return true;
    }
}
