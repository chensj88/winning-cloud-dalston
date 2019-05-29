package com.winning.devops.jwt.auth.server.repository;

import com.winning.devops.jwt.auth.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.repository
 * @date: 2019-05-17 13:32
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
