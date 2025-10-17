package com.moneymesh.moneymesh.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JWTService {
    @Value("${JWT_SECRET}")
    private String secretKey;

    final Algorithm algorithm = Algorithm.HMAC512("secretKey");

    public String createJWT(String username) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());
        final Date now = cal.getTime();
        cal.add(Calendar.HOUR, 24);
        final Date expiresAt = cal.getTime();

        return JWT.create().withIssuer("Test").withExpiresAt(expiresAt).withSubject(username).sign(algorithm);
    }

    public boolean tokenValid(String jwtToken) {
        return JWT.decode(jwtToken).getExpiresAt().after(new Date());
    }

    public String getUsername(String jwtToken) {
        return JWT.decode(jwtToken).getSubject();
    }
}
