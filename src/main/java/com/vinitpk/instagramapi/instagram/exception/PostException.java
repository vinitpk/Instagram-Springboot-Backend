package com.vinitpk.instagramapi.instagram.exception;

/**
 * Custom exception class for handling exceptions related to posts in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
public class PostException extends Exception {

    // Default constructor
    public PostException() {
    }

    // Constructor with message
    public PostException(String message) {
        super(message);
    }
}
