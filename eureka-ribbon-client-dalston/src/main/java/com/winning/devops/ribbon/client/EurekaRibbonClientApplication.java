package com.winning.devops.ribbon.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title Eureka Ribbon Client
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon.client
 * @date: 2019-04-20 14:35
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaRibbonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonClientApplication.class, args);
    }
}
