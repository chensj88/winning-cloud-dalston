package com.winning.devops.feign.client.web.handler;

import com.winning.devops.feign.client.web.service.EurekaFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title HiHystrix 逻辑处理
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.web.handler
 * @date: 2019-04-23 14:27
 */
@Component
public class HiHystrix implements EurekaFeignClient {
    @Override
    public String sayHiFromFeignEurekaClient(String username) {
        return "Hi," +username +",sorry,error!";
    }
}
