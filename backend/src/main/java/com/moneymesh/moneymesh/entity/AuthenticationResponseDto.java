package com.moneymesh.moneymesh.entity;

import lombok.Getter;

@Getter
public class AuthenticationResponseDto {
    final private String email;
    private String message = null;

    public AuthenticationResponseDto(String email, String message) {
        this.email = email;
        this.message = message;
    }
}
