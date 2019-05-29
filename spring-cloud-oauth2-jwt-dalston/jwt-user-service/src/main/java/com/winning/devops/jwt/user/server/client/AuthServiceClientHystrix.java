package com.winning.devops.jwt.user.server.client;

/**
 * @author chensj
 * @date 2019-05-29 13:37
 */
public class AuthServiceClientHystrix implements AuthServiceClient{
    @Override
    public Object getToken(String clientId, String scope, String secret, String type, String username, String password) {
        return null;
    }
}
