package com.moneymesh.moneymesh.entity;

import lombok.Getter;

@Getter
public class AuthenticationResponseDto {
    private String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }
}
