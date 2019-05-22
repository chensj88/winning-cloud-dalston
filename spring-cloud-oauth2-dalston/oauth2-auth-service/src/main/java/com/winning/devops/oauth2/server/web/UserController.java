package com.winning.devops.oauth2.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.oauth2.server.web
 * @date: 2019-05-22 21:36
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public Principal getUser(Principal principal){
        return principal;
    }
}
