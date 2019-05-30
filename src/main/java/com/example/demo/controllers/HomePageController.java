package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {


    @GetMapping("/login")
    public String homePage() {
        return "login";
    }

    @GetMapping("/user")
    public String homeUser() {
        return "userpage";
    }


    @GetMapping("/up")
    public String upload() {
        return "upload";
    }

    @GetMapping("/")
    public String mainPage() {
        return "mainpage";
    }



}
