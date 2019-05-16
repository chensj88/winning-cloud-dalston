package com.winning.devops.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author chensj
 * @title Spring Security 基础配置
 *          在内存中配置一个用户的认证信息，指定用户名、密码和角色。
 *          使用基本的信息实现认证
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.config
 * @date: 2019-05-16 10:40
 */
//@EnableWebSecurity
//@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中中创建一个用户的认证信息
        auth.inMemoryAuthentication()
                // 设置用户名称为 chensj
                .withUser("chensj")
                // 设置用户密码为 123456
                .password("123456")
                // 设置角色为 USER
                .roles("USER");

    }
}
