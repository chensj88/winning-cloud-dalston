package com.winning.devops.dalston.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @description
 * @package PACKAGE_NAME
 * @date: 2019-04-19 21:32
 */

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientDalstonApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaClientDalstonApplication.class,args);
    }
}
