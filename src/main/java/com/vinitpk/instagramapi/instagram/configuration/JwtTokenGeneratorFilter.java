package com.vinitpk.instagramapi.instagram.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Vinit Kelginmane
 * @project: instagram-api-springboot
 * @Date: 12-02-2024
 */
@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Retrieve the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // Generate a secret key using JWT_KEY from SecurityContext
            SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

            // Create a JWT token
            String jwt = Jwts.builder()
                    .setIssuer("Vinit's Instagram Clone") // Set the issuer of the token
                    .claim("authorities", populateAuthorities(authentication.getAuthorities())) // Add authorities to the claims
                    .claim("username", authentication.getName()) // Add username to the claims
                    .setIssuedAt(new Date()) // Set the issued date of the token
                    .setExpiration(new Date(System.currentTimeMillis() + (5 * 60 * 60 * 1000))) // Set expiration time
                    .signWith(key).compact(); // Sign the token with the secret key

            // Add the JWT token to the response header
            response.setHeader(SecurityContext.Header, jwt);
        }
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    // Utility method to populate authorities
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authorities.add(authority.getAuthority());
        }
        return String.join(",", authorities);
    }

    // Override shouldNotFilter to specify which requests should not be filtered
    protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) throws ServletException {
        // Exclude "/signin" endpoint from being filtered
        return !httpServletRequest.getServletPath().equals("/signin");
    }
}
