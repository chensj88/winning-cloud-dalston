package com.winning.devops.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package PACKAGE_NAME
 * @date: 2019-04-23 16:06
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
public class TurbineMonitorApplication {

    public static void main(String[] args){
        SpringApplication.run(TurbineMonitorApplication.class,args);
    }

}
