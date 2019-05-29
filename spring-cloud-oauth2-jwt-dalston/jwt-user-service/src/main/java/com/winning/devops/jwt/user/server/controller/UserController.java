package com.winning.devops.jwt.user.server.controller;

import com.winning.devops.jwt.user.server.model.User;
import com.winning.devops.jwt.user.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @date 2019-05-29 13:12
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public User add(@RequestParam("username") String username,@RequestParam("password") String password){
        return userService.save(username,password);
    }
    @PostMapping("login")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.login(username,password);
    }
}
