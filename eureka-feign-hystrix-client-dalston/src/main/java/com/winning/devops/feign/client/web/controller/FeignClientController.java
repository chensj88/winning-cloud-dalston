package com.winning.devops.feign.client.web.controller;

import com.winning.devops.feign.client.base.Constants;
import com.winning.devops.feign.client.web.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.web
 * @date: 2019-04-21 4:36
 */
@RestController
public class FeignClientController {

    @Autowired
    private SayHelloService sayHelloService;

    /**
     * 测试请求eureka-client
     * @param username 用户名
     * @return map
     */
    @PostMapping(value = "/hi/{username}")
    public Map<String,Object> hi(@PathVariable String username){
        Map<String,Object> resultMap = new HashMap<>(5);
        resultMap.put("data", sayHelloService.sayHi(username));
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }

}
