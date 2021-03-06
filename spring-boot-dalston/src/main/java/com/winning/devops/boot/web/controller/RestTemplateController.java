package com.winning.devops.boot.web.controller;

import com.winning.devops.boot.base.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title RestTemplate 控制器
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.web.controller
 * @date: 2019-04-20 13:55
 */
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * RestTemplate 常用方法：
     *
     *   delete() 在特定的URL上对资源执行HTTP DELETE操作
     *   exchange() 在URL上执行特定的HTTP方法，返回包含对象的ResponseEntity，这个对象是从响应体中映射得到的
     *   execute() 在URL上执行特定的HTTP方法，返回一个从响应体映射得到的对象
     *   getForEntity() 发送一个HTTP GET请求，返回的ResponseEntity包含了响应体所映射成的对象
     *   getForObject() 发送一个HTTP GET请求，返回的请求体将映射为一个对象
     *   postForEntity() POST 数据到一个URL，返回包含一个对象的ResponseEntity，这个对象是从响应体中映射得到的
     *   postForObject() POST 数据到一个URL，返回根据响应体匹配形成的对象
     *   headForHeaders() 发送HTTP HEAD请求，返回包含特定资源URL的HTTP头
     *   optionsForAllow() 发送HTTP OPTIONS请求，返回对特定URL的Allow头信息
     *   postForLocation() POST 数据到一个URL，返回新创建资源的URL
     *   put() PUT 资源到特定的URL
     */
    @PostMapping(value = "/hello")
    public Map<String,Object> hello(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",restTemplate.getForObject("https://www.baidu.com",String.class));
        resultMap.put("status", Constants.SUCCESS_STATUS);
        return resultMap;
    }
}
