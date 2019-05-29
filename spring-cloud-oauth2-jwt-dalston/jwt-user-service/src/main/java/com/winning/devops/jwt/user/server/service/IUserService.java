package com.winning.devops.jwt.user.server.service;

import com.winning.devops.jwt.user.server.model.User;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service
 * @date: 2019-05-17 13:36
 */
public interface IUserService {
    /**
     * 用户创建或保存
     * @param username
     * @param password
     * @return
     */
    User save(String username,String password);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Object login(String username, String password);
}
