package com.winning.devops.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author chensj
 * @title Http Spring Security 配置，主要配置
 *      那些资源需要验证或者不需要验证、登录页面、退出页面、登录成功等
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.config
 * @date: 2019-05-16 10:52
 * {@code @EnableGlobalMethodSecurity} 开启方法级别的保护，可选参数如下
 *      prePostEnabled：Spring Security的Pre和Post注解是否可用，即@PreAuthorize和@PostAuthorize是否可用
 *      securedEnabled：Spring Security的@Secured注解是否可用
 *      jsr250Enabled： Spring Security的JSR-250注解是否可用
 */
//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 认证所有的请求
                .authorizeRequests()
                // 下面这些请求不验密
                .mvcMatchers("/css/**","/","/index","/js/**","/static/**").permitAll()
                // 下面这个资源需要验密，并且需要角色USER
                .mvcMatchers("/user/**").hasRole("USER")
                // 下面这个资源需要权限验证，角色是USER或者ADMIN都可
                .mvcMatchers("/blog/**").hasAnyRole("USER","ADMIN")
                .and()
                // 表单登录
                .formLogin()
                // 表单页面
                .loginPage("/login")
                // 表单登录失败
                .failureUrl("/login-error")
                // 密码参数名
                .passwordParameter("passwd")
                // 用户名参数名
                .usernameParameter("username")
                // 登录成功的页面，默认为/
                //.successForwardUrl("/home")
                .and()
                // 异常处理会重定向到401
                .exceptionHandling().accessDeniedPage("/401");
        // 注销成功会重定向到首页
        http.logout().logoutSuccessUrl("/");
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("chensj").password("123456").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123456").roles("ADMIN").build());
        return manager;
    }
}
