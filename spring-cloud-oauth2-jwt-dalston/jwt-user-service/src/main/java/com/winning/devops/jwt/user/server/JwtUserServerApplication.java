package com.winning.devops.jwt.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author chensj
 * @date 2019-05-29 12:51
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
public class JwtUserServerApplication {
    public static void main(String[] args){
        SpringApplication.run(JwtUserServerApplication.class,args);
    }
}
