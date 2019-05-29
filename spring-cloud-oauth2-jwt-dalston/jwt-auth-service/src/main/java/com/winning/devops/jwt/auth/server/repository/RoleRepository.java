package com.winning.devops.jwt.auth.server.repository;

import com.winning.devops.jwt.auth.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.repository
 * @date: 2019-05-17 13:34
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
