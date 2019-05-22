package com.winning.devops.service.hi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author chensj
 * @title 验证测试类
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date: 2019-05-23 0:07
 */
@RestController
public class HiController {

    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(HiController.class);
    @Value("${server.port}")
    private  String port;

    /**
     * 不需要任何权限，只需要验证header中token是否正确
     * token 正确即可访问
     * @return
     */
    @RequestMapping("hi")
    public String hi(){
        return "hi , i'm from port:"+port;
    }

    /**
     * 需要ROLE_ADMIN的角色
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @RequestMapping("hello")
    public String hello(){
        return "user has admin";
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication,
                                             Principal principal,
                                             Authentication authentication){
        logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        logger.info(oAuth2Authentication.toString());
        logger.info("principal.toString()"+principal.toString());
        logger.info("principal.getName()"+principal.getName());
        logger.info("authentication"+authentication.getAuthorities().toString());
        return oAuth2Authentication;
    }
}
