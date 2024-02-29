package com.vinitpk.instagramapi.instagram.security;

import com.vinitpk.instagramapi.instagram.configuration.SecurityContext;
import com.vinitpk.instagramapi.instagram.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Service responsible for generating and parsing JWT tokens.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
@Service
public class JwtTokenProvider {

    // Method to parse JWT token and extract claims
    public JwtTokenClaims getClaimsFromToken(String token){
        // Create a secret key from the JWT key defined in the SecurityContext
        SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

        // Parse the JWT token and extract its claims
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        // Extract the username claim
        String username = String.valueOf(claims.get("username"));

        // Create a JwtTokenClaims object and set the extracted username
        JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
        jwtTokenClaims.setUsername(username);

        return jwtTokenClaims;
    }

    // Method to generate a new JWT token for a user
    public String  generateJwtToken(User user) {
        // Create a secret key from the JWT key defined in the SecurityContext
        SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

        // Build and sign a new JWT token with user's email as the username claim
        return Jwts.builder()
                .setIssuer("Vinit's Instagram Clone")
                .claim("username", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 5 * 60 * 60 * 1000)) // Token expiration time: 5 hours
                .signWith(key).compact();
    }
}
