package com.winning.devops.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.gateway.server
 * @date: 2019-06-06 10:57
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class MysqlGatewayServerApplication {

    public static void main(String[] args){
        SpringApplication.run(MysqlGatewayServerApplication.class,args);
    }

}
