package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides user details service for authentication.
 * Implements Spring Security's UserDetailsService.
 * Retrieves user details from the database based on the username.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
@Service
public class UserUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user details by username.
     *
     * @param username The username of the user to load details for
     * @return UserDetails object containing user details
     * @throws UsernameNotFoundException If the user is not found
     * @throws BadCredentialsException   If there are bad credentials
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, BadCredentialsException {

        Optional<com.vinitpk.instagramapi.instagram.model.User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isPresent()){
            com.vinitpk.instagramapi.instagram.model.User user = optionalUser.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            return new User(user.getEmail(), user.getPassword(), authorities);
        }

        throw new BadCredentialsException("User not found with username: " + username);
    }
}
