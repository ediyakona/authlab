package com.example.authlab.user.dto;

public class UserLoginResponse {

    private final String loginId;
    private final String role;

    public UserLoginResponse(String loginId, String role) {
        this.loginId = loginId;
        this.role = role;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getRole() {
        return role;
    }
}