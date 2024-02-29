package com.vinitpk.instagramapi.instagram.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

/**
 * Custom filter to validate and process JWT token from the request header.
 * Retrieves the JWT token from the request header, validates it, extracts user information,
 * and sets the authentication in the security context.
 */
/**
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 12-02-2024
 */
@Component
public class JwtTokenValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Retrieve JWT token from request header
        String jwt = request.getHeader(SecurityContext.Header);

        if (jwt != null) {
            try {
                // Remove "Bearer " prefix from token
                jwt = jwt.substring(7);

                // Validate and parse JWT token
                SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                // Extract username and authorities from token claims
                String userName = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

                // Convert authorities string to list of GrantedAuthority objects
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create authentication object
                Authentication auth = new UsernamePasswordAuthenticationToken(userName, null, auths);

                // Set authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // Throw exception for invalid token
                throw new BadCredentialsException("Invalid Token Received...");
            }
        }
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) throws ServletException {
        // Exclude "/signin" endpoint from being filtered
        return httpServletRequest.getServletPath().equals("/signin");
    }
}
