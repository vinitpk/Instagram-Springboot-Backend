package com.vinitpk.instagramapi.instagram.exception;

/**
 * Custom exception class for handling exceptions related to users in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 09-02-2024
 */
public class UserException extends RuntimeException {

    // Default constructor
    public UserException() {
    }

    // Constructor with message
    public UserException(String message) {
        super(message);
    }
}
