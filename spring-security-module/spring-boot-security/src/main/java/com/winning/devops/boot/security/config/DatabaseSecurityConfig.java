package com.winning.devops.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.config
 * @date: 2019-05-17 13:35
 */
@EnableWebSecurity
@Configurable
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
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
         auth.userDetailsService(userDetailsService);

    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("chensj").password("123456").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("123456").roles("ADMIN").build());
//        return manager;
//    }
}
