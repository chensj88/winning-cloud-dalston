package com.winning.devops.rabbitmq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package PACKAGE_NAME
 * @date 2019-06-06 20:48
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinStreamServer
public class RabbitmqZipkinServerApplication {

    public static void main(String[] args){
        SpringApplication.run(RabbitmqZipkinServerApplication.class,args);
    }
}
