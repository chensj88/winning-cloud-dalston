package com.winning.devops.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon
 * @date: 2019-04-20 17:06
 */
@SpringBootApplication
public class RibbonClientApplication {

    public static void main(String[] args){
        SpringApplication.run(RibbonClientApplication.class,args);
    }
}
