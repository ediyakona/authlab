package com.example.authlab.user;

public enum UserMessage {

    MSG_1("이미 사용 중인 로그인 아이디입니다."),
    MSG_2("아이디 또는 비밀번호가 올바르지 않습니다.");

    private final String message;

    private UserMessage(String message) {
        this.message = message;
    }

    public String getLocaleMessage() {
        return message;
    }

}
