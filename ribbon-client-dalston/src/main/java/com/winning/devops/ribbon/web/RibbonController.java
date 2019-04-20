package com.winning.devops.ribbon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.ribbon
 * @date: 2019-04-20 17:17
 */
@RestController
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * 这个方法可以知道：
     *  在Ribbon 中的负载均衡客户端为LoadBalancerClient 。在Spring Cloud项目中，
     *  负载均衡器 Ribbon 会默认从Eureka Client 的服务注册列表中获取服务的信息，并缓存。
     *  根据缓存的服务注册列表信息，可以通过LoadBalancerClient来选择不同的服务实例，
     *  从而实现负载均衡。如果禁止Ribbon Eureka获取注册列表信息，则需要自己去维护一份服
     *  务注册列表信息。 根据自己维护服务注册列表的信息，Ribbon可以实现负载均衡。
     * @return
     */
    @PostMapping(value = "/testRibbon")
    public String testRibbon(){
        // 从配置文件中获取stores的服务端信息
        ServiceInstance instance = loadBalancer.choose("stores");
        return instance.getHost()+":"+instance.getPort();
    }
}
