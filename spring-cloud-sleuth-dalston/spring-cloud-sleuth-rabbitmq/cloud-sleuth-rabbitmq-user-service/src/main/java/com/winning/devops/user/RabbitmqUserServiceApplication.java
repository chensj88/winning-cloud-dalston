package com.winning.devops.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.user
 * @date: 2019-06-06 21:05
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitmqUserServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(RabbitmqUserServiceApplication.class,args);
    }
}
