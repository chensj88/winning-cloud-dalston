package com.winning.devops.feign.client.web.service;

import com.winning.devops.feign.client.base.Constants;
import com.winning.devops.feign.client.base.config.FeignClientConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.feign.client.service
 * @date: 2019-04-21 4:28
 */
@FeignClient(value = Constants.DEFAULT_EUREKA_CLIENT_NAME,
             configuration = FeignClientConfig.class)
public interface EurekaFeignClient {
    /**
     * feign请求
     * @param username
     * @return
     */
    @GetMapping(value = "/hi/{username}")
    String sayHiFromFeignEurekaClient(@PathVariable(value="username") String username);
}
