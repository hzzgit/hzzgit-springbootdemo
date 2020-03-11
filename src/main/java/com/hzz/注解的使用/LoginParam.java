package com.hzz.注解的使用;

import lombok.Data;

@Data
public class LoginParam {

    private String userName;

    private String password;

    public LoginParam(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}