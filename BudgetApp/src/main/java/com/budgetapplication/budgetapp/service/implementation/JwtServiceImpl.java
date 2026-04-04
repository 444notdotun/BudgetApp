package com.budgetapplication.budgetapp.service.implementation;

import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.service.interfac.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String jwtSecretKey;
    @Value("${jwt.expiration}")
    private Long expiration;


    @Override
    public String generateToken(Users user) {
        return Jwts.builder()
                .signWith(getSecretKey())
                .subject(user.getUserId())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .issuedAt(new Date())
                .compact();

    }

    @Override
    public boolean validateToken(String token) {
        try{
            extractClaims(token);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    private SecretKey getSecretKey() {
        byte[] encodedKey = Base64.getDecoder().decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}
