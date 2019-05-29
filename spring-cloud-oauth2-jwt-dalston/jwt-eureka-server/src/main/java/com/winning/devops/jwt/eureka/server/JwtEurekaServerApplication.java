package com.winning.devops.jwt.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @date 2019-05-29 10:13
 */
@SpringBootApplication
@EnableEurekaServer
public class JwtEurekaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(JwtEurekaServerApplication.class,args);
    }
}
