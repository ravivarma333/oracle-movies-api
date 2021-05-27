package com.ora.movieapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }


    @RequestMapping("/logout-success")
    public String logoutPage(){
        return "logout";
    }
}
