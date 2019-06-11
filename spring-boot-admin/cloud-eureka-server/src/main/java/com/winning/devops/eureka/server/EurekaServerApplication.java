package com.winning.devops.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * $END$
 *
 * @author chensj
 * @title
 * @date 2019-06-11 22:10
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args){
         SpringApplication.run(EurekaServerApplication.class,args);
    }
}
