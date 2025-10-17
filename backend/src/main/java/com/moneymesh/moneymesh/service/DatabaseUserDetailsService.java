package com.moneymesh.moneymesh.service;

import com.moneymesh.moneymesh.entity.DatabaseUserDetails;
import com.moneymesh.moneymesh.entity.User;
import com.moneymesh.moneymesh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findUserByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("");
        }

        User user = userOptional.get();

        return new DatabaseUserDetails(user.getEmail(), user.getPassword());

    }
}

