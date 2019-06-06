package com.winning.devops.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

/**
 * 链路数据追踪服务端，记录链路数据
 * @author chensj
 * @date 2019年06月06日 10:24
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class MysqlSleuthZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlSleuthZipkinServerApplication.class,args);
    }

    @Bean
    public MySQLStorage mySQLStorage(DataSource dataSource){
        return MySQLStorage.builder().datasource(dataSource).executor(Runnable::run).build();
    }

}
