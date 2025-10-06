package com.moneymesh.moneymesh.controller;

import com.moneymesh.moneymesh.entity.AuthenticationResponseDto;
import com.moneymesh.moneymesh.entity.UserDataDto;
import com.moneymesh.moneymesh.exception.CoultNotAuthorizeUserException;
import com.moneymesh.moneymesh.exception.UserAlreadyExistsException;
import com.moneymesh.moneymesh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> authorizeUser(@RequestBody UserDataDto userdata) {
        try {
            final String jwt = this.userService.authorizeUser(userdata);
            System.out.println(jwt);
            return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
        } catch (CoultNotAuthorizeUserException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not authorize user");
        }
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody UserDataDto userdata) {
        try {
            userService.createUser(userdata.getEmail(), userdata.getPassword());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not create user");
        }

        return  ResponseEntity.ok("user successfully created");
    }
}
