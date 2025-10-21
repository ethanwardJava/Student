package com.arcade.bootapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //Just a greet message
    @GetMapping("/")
    public static String greet() {
        return "Spring Boot Application is running";
    }
}
