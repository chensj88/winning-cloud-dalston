package com.winning.devops.jwt.user.server.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chensj
 * @date 2019-05-29 13:32
 */
@FeignClient(value = "jwt-auth-service",fallback = AuthServiceClientHystrix.class)
public interface AuthServiceClient{

    @PostMapping(value = "/oauth/token")
    Object getToken(
            @RequestParam(value = "clientId") String clientId,
            @RequestParam(value = "scope") String scope,
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "grant_type") String type,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    );
}
