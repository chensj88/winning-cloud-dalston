package com.winning.devops.boot.web.service.impl;

import com.winning.devops.boot.web.dao.UserRepository;
import com.winning.devops.boot.web.model.User;
import com.winning.devops.boot.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.web.service.impl
 * @date: 2019-04-20 13:38
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}
