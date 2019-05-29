package com.winning.devops.jwt.user.server.service.impl;

import com.winning.devops.jwt.user.server.client.AuthServiceClient;
import com.winning.devops.jwt.user.server.exception.UserLoginException;
import com.winning.devops.jwt.user.server.model.User;
import com.winning.devops.jwt.user.server.repository.UserRepository;
import com.winning.devops.jwt.user.server.service.IUserService;
import com.winning.devops.jwt.user.server.utils.BCryptPasswordEncoderUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service.impl
 * @date: 2019-05-17 13:37
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthServiceClient authServiceClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    @Override
    public User save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(BCryptPasswordEncoderUtils.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Object login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserLoginException("error username");
        }
        if(!BCryptPasswordEncoderUtils.mathes(password,user.getPassword())){
            throw new UserLoginException("Username and Password not matcher!");
        }
        Object obj = authServiceClient.getToken("user-service","service","123456","password",username,password);
        System.out.println(ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE));
        return obj;
    }
}
