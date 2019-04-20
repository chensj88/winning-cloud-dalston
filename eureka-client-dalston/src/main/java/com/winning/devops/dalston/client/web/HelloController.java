package com.winning.devops.dalston.client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @description
 * @package com.winning.devops.dalston.client.web
 * @date: 2019-04-19 22:05
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/hi/{username}")
    public String hello(@PathVariable String username){
        return "Hello, "+username+" @ "+ port;
    }

}
