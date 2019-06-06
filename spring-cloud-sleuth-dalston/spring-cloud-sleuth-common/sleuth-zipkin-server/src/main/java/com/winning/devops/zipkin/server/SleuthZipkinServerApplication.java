package com.winning.devops.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/**
 * 链路数据追踪服务端，记录链路数据
 * @author chensj
 * @date 2019年06月06日 10:24
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class SleuthZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinServerApplication.class,args);
    }

}
