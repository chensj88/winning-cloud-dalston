package com.winning.devops.service.hi.web;

import com.winning.devops.service.hi.service.IUserService;
import com.winning.devops.service.hi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title 用户注册接口
 * @project winning-cloud-dalston
 * @package com.winning.devops.service.hi
 * @date 2019-05-23 0:03
 * 用户注册： curl -d "username=chen&password=123456" "localhost:8762/users/register"
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public User createUser(@RequestParam("username")String username, @RequestParam("password") String password){
        return userService.create(username,password);
    }

}
