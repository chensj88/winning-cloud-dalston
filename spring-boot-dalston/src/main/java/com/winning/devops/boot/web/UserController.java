package com.winning.devops.boot.web;

import com.winning.devops.boot.base.Constants;
import com.winning.devops.boot.web.model.User;
import com.winning.devops.boot.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.web
 * @date: 2019-04-20 13:28
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/{username}/{password}")
    public Map<String,Object> addUser(@PathVariable String username, @PathVariable String password){
        Map<String,Object> resultMap = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        resultMap.put("data",userService.createUser(user));
        resultMap.put("status", Constants.SUCCESS_STATUS);
        return resultMap;
    }

    @PostMapping(value = "/user/list")
    public Map<String,Object> list(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",userService.list());
        resultMap.put("status", Constants.SUCCESS_STATUS);
        return resultMap;
    }

}
