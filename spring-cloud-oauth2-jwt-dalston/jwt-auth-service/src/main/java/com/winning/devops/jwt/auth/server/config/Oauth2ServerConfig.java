package com.winning.devops.jwt.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author chensj
 * @date 2019-05-29 10:49
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
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
     * 客户端信息
     * 注册两个客户端：
     * user-service   service  123456
     * post-service   post     1
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("user-service")
                .scopes("service")
                .secret("123456")
                .authorizedGrantTypes("refresh_token", "password")
                .accessTokenValiditySeconds(3600)
                .and()
                .withClient("post-service")
                .secret("1")
                .scopes("post")
                .authorizedGrantTypes("refresh_token", "password")
                .accessTokenValiditySeconds(3600);
    }

    /**
     * endpoint 配置tokenStore和authenticationManager
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .tokenEnhancer(jwtAccessTokenConverter());
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置TokenStore
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Token 转换器
     *
     * @return
     */
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 秘钥生成
        KeyStoreKeyFactory factory =
                new KeyStoreKeyFactory(
                        new ClassPathResource("chen-jwt.jks"),
                        "chen123".toCharArray()
                );
        JwtAccessTokenConverter jwtAccessTokenConverter =
                new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(factory.getKeyPair("chen-jwt"));
        return jwtAccessTokenConverter;

    }
}
