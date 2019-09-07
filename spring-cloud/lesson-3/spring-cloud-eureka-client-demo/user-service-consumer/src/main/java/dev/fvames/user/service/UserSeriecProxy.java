package dev.fvames.user.service;

import dev.fvames.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * {@link UserService 用户服务} Proxy 实现
 */
public class UserSeriecProxy implements UserService{

    public static final String PROVIDER_SERVER_URL_PROXY = "http://user-service-provider";

    /**
     * 通过 REST API 代理到服务器提供者
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean save(User user) {
        User resultValue = restTemplate.postForObject(PROVIDER_SERVER_URL_PROXY + "/user/save", user, User.class);
        return resultValue == null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PROXY + "/user/list", Collection.class);
    }
}
