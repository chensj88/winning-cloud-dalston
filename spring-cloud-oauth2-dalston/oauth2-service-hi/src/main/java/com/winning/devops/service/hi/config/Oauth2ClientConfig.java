package com.winning.devops.service.hi.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author chensj
 * @title
 * {@code @EnableOAuth2Client}  开启OAuth2 Client的功能
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date 2019-05-22 23:45
 */
@EnableOAuth2Client
@EnableConfigurationProperties
@Configurable
public class Oauth2ClientConfig {
    /**
     * 配置一个受保护的资源
     * 配置一个ClientCredentialsResourceDetails类型Bean
     * 通过读取配置文件中前缀为security.oauth2.client的配置来获获取Bean的配置属性的
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails(){
        return new ClientCredentialsResourceDetails();
    }

    /**
     * 配置一个过滤器
     * 注入一个OAuth2FeignRequestInterceptor类型过滤器的Bean
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(),clientCredentialsResourceDetails());
    }

    /**
     * 在Request域内创建AccessTokenRequest型的Bean
     * 注入了一个用Auth Server服务请求的OAuth2RestTemplate型的Bean
     * @return
     */
    @Bean
    public OAuth2RestTemplate clientCredentialsOAuth2RestTemplate(){
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
