package com.winning.devops.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.oauth2.server
 * @date: 2019-05-22 13:15
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class Oauth2ServerApplication {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

    @Configurable
    @EnableAuthorizationServer
    protected class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {


        @Autowired
        @Qualifier("authenticationManager")
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);

        /**
         * 用来配置令牌端点(Token Endpoint)的安全约束.
         * @param security
         * @throws Exception 异常
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

            security // 允许表单认证
                    .allowFormAuthenticationForClients()
                    // r 配置了获取 Token 的策略
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        /**
         * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
         * @param clients 客户端配置
         * @throws Exception 异常
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients
                    // 客户端信息放置在内存中
                    .inMemory()
                    // 创建一个clientID为client_1的客户端
                    .withClient("browser")
                    // 客户端密码
                    .secret("123456")
                    // 配置了验证类型为refresh_token和password,
                    .authorizedGrantTypes("refresh_token", "password")
                    // 客户端域为ui
                    .scopes("ui")
                    .and()
                    .withClient("service-hi")
                    .secret("123456")
                    .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                    .scopes("server");
        }

        /**
         * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
         *
         * @param endpoints
         * @throws Exception 异常
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    // 配置了一个tokenStore
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }
    }
}
