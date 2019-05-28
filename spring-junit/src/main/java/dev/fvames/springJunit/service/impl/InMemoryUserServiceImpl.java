package dev.fvames.springJunit.service.impl;

import dev.fvames.springJunit.domain.User;
import dev.fvames.springJunit.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存实现
 *
 * @version 2019/5/27 17:56
 */
@Service
public class InMemoryUserServiceImpl implements IUserService {

    private Map<String, User> cache = new HashMap<>();

    @Override
    public boolean save(User user) {
        return cache.put(user.getId(), user) == null;
    }
}
