package com.winning.devops.boot.security.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.model
 * @date: 2019-05-17 13:20
 */
@Data
@Entity
@Table(name = "sec_user")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sec_user_role",
        joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private List<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
