package com.moneymesh.moneymesh.entity;

public class UserDataDto {
    final private String email;
    final private String password;

    public UserDataDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
