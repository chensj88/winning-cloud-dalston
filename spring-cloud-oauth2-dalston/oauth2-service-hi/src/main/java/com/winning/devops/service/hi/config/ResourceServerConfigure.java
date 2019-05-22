package com.winning.devops.service.hi.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author chensj
 * @description
 *  {@code @EnableResourceServer} 开启 ResourceServer
 *  {@code @EnableGlobalMethodSecurity} 开启方法级别的保护
 *  ResourceServerConfigurer继承ResourceServerConfigurerAdapter 类， 并重写configure(HttpSecurity http)方法
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date 2019-05-22 23:37
 */
@Configurable
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //
                .authorizeRequests()
                // ant表达式 配置
                .antMatchers("/users/register")
                // 无需验证
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
