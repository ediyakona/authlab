package com.example.authlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("HELLO");
        return "Hello, World!223555623225";
    }


}
