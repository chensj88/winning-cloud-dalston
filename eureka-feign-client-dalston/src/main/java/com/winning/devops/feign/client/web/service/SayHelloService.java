package com.winning.devops.feign.client.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.service
 * @date: 2019-04-21 4:34
 */
@Service
public class SayHelloService {

    @Autowired
    private EurekaFeignClient eurekaFeignClient;

    public String sayHi(String username){
        return eurekaFeignClient.sayHiFromFeignEurekaClient(username);
    }
}
