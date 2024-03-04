package com.vinitpk.instagramapi.instagram.controller;

import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.repository.UserRepository;
import com.vinitpk.instagramapi.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class to handle user authentication-related endpoints.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
@RestController
public class AuthController {

    // Autowire UserService and UserRepository for user-related operations
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint for user registration
    @PostMapping("/signup")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        // Call UserService to register the user
        User createdUser = userService.registerUser(user);
        // Return response with the created user and HTTP status code 201 (Created)
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    // Endpoint for user sign-in
    @GetMapping("/signin")
    public ResponseEntity<User> signinUserHandler(Authentication authentication) throws BadCredentialsException {
        try {
            // Find user by email (username) from UserRepository
            User user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new BadCredentialsException("Invalid User Name or Password"));
            // Return response with the authenticated user and HTTP status code 202 (Accepted)
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
        } catch (BadCredentialsException e) {
            // Throw BadCredentialsException if user not found or authentication fails
            throw new BadCredentialsException("Invalid User Name or Password");
            
        }
    }
}
