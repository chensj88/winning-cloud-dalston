package com.winning.devops.boot.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.web
 * @date: 2019-05-16 11:30
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String root(){
        return "redirect:/index";
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/user/index")
    public String userIndex(){
        return "user/index";
    }
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
    @RequestMapping(value = "/401")
    public String accessDenied(){
        return "401";
    }
}
