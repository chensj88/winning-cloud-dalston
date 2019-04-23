package com.winning.devops.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author chensj
 * @title eureka client ribbon hystrix hystrix-dashboard feign
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client
 * @date: 2019-04-21 4:13
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EurekaFeignHystrixClientApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaFeignHystrixClientApplication.class,args);
    }
}
