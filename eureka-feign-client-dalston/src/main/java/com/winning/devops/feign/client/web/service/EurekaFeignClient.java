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
    //A central concept in Spring Cloud’s Feign support is that of the named client.
    // Spring Cloud的Feign支持的核心概念是指定客户端的概念。
    // Each feign client is part of an ensemble of components that work together to contact a remote server on demand,
    // 每个客户端都是组件集合的一部分，它们一起工作并且按需联系远程服务器，
    // and the ensemble has a name that you give it as an application developer using the `@FeignClient` annotation.
    // 这样的集合都有一个名称，应用程序开发人员可以使用`@FeignClient`注解来提供名称。
    // Spring Cloud creates a new ensemble as an `ApplicationContext` on demand for each named client using `FeignClientsConfiguration`.
    // Spring Cloud根据ApplicationContext需要为每个命名客户端创建一个使用`FeignClientsConfiguration`创建新的集合
    // This contains (amongst other things) an `feign.Decoder`, a `feign.Encoder`, and a `feign.Contract`.
    // 这些包含`feign.Decoder`、 `feign.Encoder`和 `feign.Contract`
    /**
     * feign请求
     * @param username 用户名称
     * @return
     */
    @GetMapping(value = "/hi/{username}")
    String sayHiFromFeignEurekaClient(@PathVariable(value="username") String username);
}
