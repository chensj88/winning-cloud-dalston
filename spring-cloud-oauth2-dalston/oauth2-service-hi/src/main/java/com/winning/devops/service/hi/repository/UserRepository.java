package com.winning.devops.service.hi.repository;

import com.winning.devops.service.hi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date: 2019-05-22 23:58
 */
public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByUsername(String username);
}
