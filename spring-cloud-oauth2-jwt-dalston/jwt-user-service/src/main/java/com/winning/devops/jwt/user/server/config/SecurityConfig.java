package com.winning.devops.jwt.user.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 开启方法级别的安全验证
 * @author chensj
 * @date 2019-05-29 13:01
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

}
