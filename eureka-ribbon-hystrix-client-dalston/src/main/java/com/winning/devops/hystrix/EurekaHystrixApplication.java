package com.winning.devops.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author chensj
 * @desc Eureka Ribbon Hystrix Application
 * @package com.winning.devops.hystrix
 * @date: 2019-04-23 13:32
 * {@code @EnableEurekaClient} 启用Eureka Client
 * {@code @EnableHystrix } 启用Hystrix
 * {@code @EnableHystrixDashboard} 启用Hystrix Dashboard
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaHystrixApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaHystrixApplication.class,args);
    }

}
