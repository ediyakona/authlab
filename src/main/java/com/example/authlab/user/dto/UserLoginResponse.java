package com.example.authlab.user.dto;

public class UserLoginResponse {

    private final String loginId;
    private final String role;
    private final String accessToken;

    public UserLoginResponse(String loginId, String role, String accessToken) {
        this.loginId = loginId;
        this.role = role;
        this.accessToken = accessToken;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getRole() {
        return role;
    }

    public String getAccessToken() {
        return accessToken;
    }
}