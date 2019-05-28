package dev.fvames.springJunit.service;

import dev.fvames.springJunit.domain.User;

import java.util.List;

/**
 * 用户服务
 *
 * @version 2019/5/27 17:55
 */

public interface RemoteUserService {

    boolean save(User user);

    List<User> findAll();
}
