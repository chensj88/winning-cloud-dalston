package com.winning.devops.jwt.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-05-29 10:25
 */
@SpringBootApplication
@EnableEurekaClient
public class JwtAuthServerApplication {
    public static void main(String[] args){
        SpringApplication.run(JwtAuthServerApplication.class,args);
    }
}
