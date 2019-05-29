package com.winning.devops.jwt.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chensj
 * @date 2019-05-29 10:26
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * AuthenticationManagerBuilder 配置了验证用户信息和密码加密的策略
     * 向IoC容器注入了AuthenticationManager对象，这个对象在OAuth2配置中使用，才会开启密码验证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 用户详情
                .userDetailsService(userDetailsService)
                // 采用BCryptPasswordEncoder对密码进行加密
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置了验证管理的Bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * HttpSecurity中配置了所有的请求都需要安全验证
     * 针对无权限返回执行错误码
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/oauth/**").permitAll()
//                .and()
//                .httpBasic();

        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .httpBasic();
    }
}
