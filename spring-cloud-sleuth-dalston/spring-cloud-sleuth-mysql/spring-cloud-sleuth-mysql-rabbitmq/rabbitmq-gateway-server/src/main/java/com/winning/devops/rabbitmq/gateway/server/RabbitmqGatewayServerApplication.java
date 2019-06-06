package com.winning.devops.rabbitmq.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.rabbitmq.gateway.server
 * @date: 2019-06-06 21:00
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class RabbitmqGatewayServerApplication {

    public static void main(String[] args){
        SpringApplication.run(RabbitmqGatewayServerApplication.class,args);
    }
}
