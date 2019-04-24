package com.winning.devops.dalston.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @description
 * @package com.winning.devops.dalston.server
 * @date: 2019-04-19 21:26
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerDalstonApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaServerDalstonApplication.class,args);
    }

}
