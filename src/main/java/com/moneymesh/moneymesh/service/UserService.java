package com.moneymesh.moneymesh.service;

import com.moneymesh.moneymesh.entity.User;
import com.moneymesh.moneymesh.entity.UserDataDto;
import com.moneymesh.moneymesh.exception.CoultNotAuthorizeUserException;
import com.moneymesh.moneymesh.exception.UserAlreadyExistsException;
import com.moneymesh.moneymesh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String email, String password) throws UserAlreadyExistsException {
        if (doesUserExist(email)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(email, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    private boolean doesUserExist(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);

        return userOptional.isPresent();
    }

    public String authorizeUser(UserDataDto userdata) throws CoultNotAuthorizeUserException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userdata.getEmail(), userdata.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findUserByEmail(userdata.getEmail()).orElseThrow();

        return this.jwtService.createJWT(user.getEmail());
    }

    public User getCurrentLoggedInUser() throws CoultNotAuthorizeUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        return userRepository.findUserByEmail(userEmail).orElseThrow();
    }
}
