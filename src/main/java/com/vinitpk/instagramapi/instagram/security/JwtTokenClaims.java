package com.vinitpk.instagramapi.instagram.security;

/**
 * Represents claims extracted from a JWT token.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
public class JwtTokenClaims {

    private String username;

    // Getter for the username claim
    public String getUsername() {
        return username;
    }

    // Setter for the username claim
    public void setUsername(String username) {
        this.username = username;
    }
}
