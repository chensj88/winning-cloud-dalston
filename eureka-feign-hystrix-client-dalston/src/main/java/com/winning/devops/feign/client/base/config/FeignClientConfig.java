package com.winning.devops.feign.client.base.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author chensj
 * @title Feign Client Config
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.base.config
 * @date: 2019-04-21 4:22
 */
@Configuration
public class FeignClientConfig {
    /**
     * Feign 在远程调用失败后会进行重试。
     * @return Retryer
     */
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }
}
