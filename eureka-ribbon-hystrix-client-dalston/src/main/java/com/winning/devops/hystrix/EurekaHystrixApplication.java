package com.winning.devops.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author chensj
 * @desc Eureka Ribbon Hystrix Application
 * @package com.winning.devops.hystrix
 * @date: 2019-04-23 13:32
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class EurekaHystrixApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaHystrixApplication.class,args);
    }

}
