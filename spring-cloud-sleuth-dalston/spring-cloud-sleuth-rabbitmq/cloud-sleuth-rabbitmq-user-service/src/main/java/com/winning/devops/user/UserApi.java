package com.winning.devops.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.user
 * @date: 2019-06-06 10:52
 */
@RestController
@RequestMapping(value = "user")
public class UserApi {

    @GetMapping(value = "hi")
    public String hi(){
        return "I'm from User Service";
    }
}
