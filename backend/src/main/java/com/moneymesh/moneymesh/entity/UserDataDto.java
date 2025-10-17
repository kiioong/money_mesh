package com.moneymesh.moneymesh.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserDataDto {
    @NotBlank
    final private String email;
    @NotBlank
    final private String password;

    public UserDataDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
