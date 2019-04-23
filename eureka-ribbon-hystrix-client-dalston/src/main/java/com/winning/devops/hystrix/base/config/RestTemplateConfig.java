package com.winning.devops.hystrix.base.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author chensj
 * @title RestTemplate 配置类 for Ribbon
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon.client.base.config
 * @date: 2019-04-20 16:33
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 实例化RestTemplate，注册bean
     * 使用@LoadBalanced就会结合Ribbon进行负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate initRestTemplate(){
        return  new RestTemplate();
    }
}
