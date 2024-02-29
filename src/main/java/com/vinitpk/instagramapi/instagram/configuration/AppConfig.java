package com.vinitpk.instagramapi.instagram.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author: Vinit Kelginmane
 * @project: instagram-api-springboot
 * @Date: 12-02-2024
 */

@Configuration
public class AppConfig {

    // Defines a SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable)
                // Authorization configuration
                .authorizeHttpRequests(
                        authorize -> authorize
                                // Permit all requests to /signup endpoint with POST method
                                .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                                // Require authentication for any other requests
                                .anyRequest().authenticated())
                // Set session creation policy to stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Add JwtTokenGeneratorFilter after BasicAuthenticationFilter
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                // Add JwtTokenValidationFilter before BasicAuthenticationFilter
                .addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
                // Configure exception handling for unauthorized access
                .exceptionHandling(e-> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                // Configure form login with default settings
                .formLogin(Customizer.withDefaults())
                // Configure HTTP Basic authentication with default settings
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // Bean for PasswordEncoder, using BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
