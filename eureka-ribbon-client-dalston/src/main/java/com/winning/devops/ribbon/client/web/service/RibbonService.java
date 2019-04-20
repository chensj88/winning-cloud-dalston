package com.winning.devops.ribbon.client.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon.client.web.service
 * @date: 2019-04-20 16:40
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 请求http://eureka-client/hi/{username}
     * @param username
     * @return
     */
    public String hi(String username){
        return restTemplate.getForObject("http://eureka-client/hi/"+username,String.class);
    }
}
