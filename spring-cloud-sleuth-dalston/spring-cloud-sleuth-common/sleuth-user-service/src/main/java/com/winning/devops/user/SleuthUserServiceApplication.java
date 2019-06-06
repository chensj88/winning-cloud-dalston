package com.winning.devops.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.user
 * @date: 2019-06-06 10:49
 */
@SpringBootApplication
@EnableEurekaClient
public class SleuthUserServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(SleuthUserServiceApplication.class,args);
    }

}
