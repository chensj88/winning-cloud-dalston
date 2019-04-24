package com.winning.devops.feign.client.web.hystrix;

import com.winning.devops.feign.client.web.feign.EurekaFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title Hystrix 错误处理
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.web.hystrix
 * @date: 2019-04-24 14:37
 */
@Component
public class EurekaHystrixFeignHandler implements EurekaFeignClient {
    @Override
    public String sayHiFromFeignEurekaClient(String username) {
        return "Hi," +username +",sorry,error!";
    }
}
