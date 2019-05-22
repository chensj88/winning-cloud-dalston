package com.winning.devops.service.hi.service;

import com.winning.devops.service.hi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date: 2019-05-23 0:00
 */
public interface IUserService extends UserDetailsService {

    User create(User user);

    User create(String username, String password);
}
