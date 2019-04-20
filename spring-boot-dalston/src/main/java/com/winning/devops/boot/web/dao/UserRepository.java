package com.winning.devops.boot.web.dao;

import com.winning.devops.boot.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.web.model
 * @date: 2019-04-20 12:54
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
