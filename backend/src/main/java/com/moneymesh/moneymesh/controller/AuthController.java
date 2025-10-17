package com.moneymesh.moneymesh.controller;

import com.moneymesh.moneymesh.entity.AuthenticationResponseDto;
import com.moneymesh.moneymesh.entity.UserDataDto;
import com.moneymesh.moneymesh.exception.CoultNotAuthorizeUserException;
import com.moneymesh.moneymesh.exception.UserAlreadyExistsException;
import com.moneymesh.moneymesh.service.JWTService;
import com.moneymesh.moneymesh.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    @Autowired
    JWTService  jwtService;

    @PostMapping("login")
    public ResponseEntity<?> authorizeUser(@Valid @RequestBody UserDataDto userdata, HttpServletResponse response) {
        try {
            final String jwt = this.userService.authorizeUser(userdata);

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            cookie.setAttribute("SameSite", "Strict");

            response.addCookie(cookie);

            return ResponseEntity.ok(new AuthenticationResponseDto(userdata.getEmail(), "login successful"));
        } catch (CoultNotAuthorizeUserException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not authorize user");
        }
    }

    @GetMapping("check")
    public ResponseEntity<?> getCurrentUser(
            @CookieValue(name = "jwt", required = false) String jwt) {

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            String username = jwtService.getUsername(jwt);
            if (jwtService.tokenValid(jwt) && userService.doesUserExist(username)) {
                return ResponseEntity.ok(new AuthenticationResponseDto(username, "user found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDataDto userdata) {
        try {
            userService.createUser(userdata.getEmail(), userdata.getPassword());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not create user");
        }

        return  ResponseEntity.ok("user successfully created");
    }
}
