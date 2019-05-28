package dev.fvames.springJunit.service;

import dev.fvames.springJunit.domain.User;

/**
 * 用户服务
 *
 * @version 2019/5/27 17:55
 */

public interface IUserService {

    boolean save(User user);

}
