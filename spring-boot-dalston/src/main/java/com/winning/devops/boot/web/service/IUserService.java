package com.winning.devops.boot.web.service;


import com.winning.devops.boot.web.model.User;

import java.util.List;

/**
 * @author chensj
 * @title user 接口
 * @date 2019年4月20日13:38:30
 */
public interface IUserService {

    public User createUser(User user);

    public List<User> list();
}
