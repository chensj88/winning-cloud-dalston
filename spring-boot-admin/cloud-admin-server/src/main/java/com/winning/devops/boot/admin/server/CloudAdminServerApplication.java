package com.winning.devops.boot.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * $END$
 *
 * @author chensj
 * @title
 * @date 2019-06-12 22:35
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
public class CloudAdminServerApplication {
    public static void main(String[] args){
         SpringApplication.run(CloudAdminServerApplication.class,args);
    }
}
