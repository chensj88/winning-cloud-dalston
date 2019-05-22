package com.winning.devops.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.oauth2.server
 * @date: 2019-05-22 13:15
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class Oauth2ServerApplication {
    public static void main(String[] args){
        SpringApplication.run(Oauth2ServerApplication.class,args);
    }
}
