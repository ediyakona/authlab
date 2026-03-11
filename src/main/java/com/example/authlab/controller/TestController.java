package com.example.authlab.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.authlab.user.UserService;
import com.example.authlab.user.dto.UserLoginRequest;
import com.example.authlab.user.dto.UserLoginResponse;
import com.example.authlab.user.dto.UserSignupRequest;

@RestController
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("HELLO");
        return "Hello, World!223555623225";
    }

    //curl -X POST http://localhost:8080/signup -H "Content-Type: application/json" -d '{"loginId":"ediyakona","password":"1234"}'
    @PostMapping("/signup")
    public String signup(@RequestBody UserSignupRequest request) {
        userService.signup(request);
        return "회원가입 완료";
    }

    //curl -X POST http://localhost:8080/login -H "Content-Type: application/json" -d '{"loginId":"ediyakona","password":"1234"}'
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/my-info")
    public Map<String, Object> myInfo(Authentication authentication) {
        return Map.of(
            "loginId", authentication.getName(),
            "authorities", authentication.getAuthorities()
        );
    }

}
