package com.winning.devops.sleuth.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package PACKAGE_NAME
 * @date: 2019-06-06 10:39
 */
@SpringBootApplication
@EnableEurekaServer
public class SleuthEurekaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(SleuthEurekaServerApplication.class,args);
    }

}
