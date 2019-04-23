package com.winning.devops.hystrix.web;

import com.winning.devops.hystrix.base.Constants;
import com.winning.devops.hystrix.web.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon.client.web
 * @date: 2019-04-20 16:40
 */
@RestController
public class RestClientController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping(value = "/hi/{username}")
    public Map hi(@PathVariable String username){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",ribbonService.hi(username));
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }

    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * 获取实例信息，从结果来看，将会是从每个客户端走一次
     * @return
     */
    @PostMapping(value = "testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancer.choose("eureka-client");
        return instance.getHost()+":"+instance.getPort();
    }
}
