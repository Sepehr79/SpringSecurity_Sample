package com.springsecuritydemo.securitydemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String redirectToMainPage(){
        return "redirect:/api";
    }

    @GetMapping("/api")
    public String getMainPage(){

        return "MainPage";
    }

    @GetMapping("/myhost")
    public String getMyHostPage(){
        return "ToManyRequests";
    }

}
