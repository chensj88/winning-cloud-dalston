package com.winning.devops.service.hi.service.impl;

import com.winning.devops.service.hi.model.User;
import com.winning.devops.service.hi.repository.UserRepository;
import com.winning.devops.service.hi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date: 2019-05-23 0:00
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    @Override
    public User create(User user) {
        String hash = ENCODER.encode(user.getPassword());
        user.setPassword(hash);
        return userRepository.save(user);
    }

    @Override
    public User create(String username, String password) {
        String hash = ENCODER.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hash);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username);
    }
}
