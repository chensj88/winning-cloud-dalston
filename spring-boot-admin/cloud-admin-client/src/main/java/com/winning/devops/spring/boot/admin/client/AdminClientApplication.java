package com.winning.devops.spring.boot.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * $END$
 *
 * @author chensj
 * @title
 * @date 2019-06-11 22:07
 */
@SpringBootApplication
@EnableEurekaClient
public class AdminClientApplication {
    public static void main(String[] args){
         SpringApplication.run(AdminClientApplication.class,args);
    }
}
