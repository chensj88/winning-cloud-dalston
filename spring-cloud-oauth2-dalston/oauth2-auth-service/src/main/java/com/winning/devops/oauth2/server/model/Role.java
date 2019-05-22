package com.winning.devops.oauth2.server.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.model
 * @date: 2019-05-17 13:23
 */
@Data
@Entity
@Table(name = "sec_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name",nullable = false)
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
