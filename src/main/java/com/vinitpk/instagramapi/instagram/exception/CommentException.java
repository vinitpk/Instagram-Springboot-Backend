package com.vinitpk.instagramapi.instagram.exception;

/**
 * Custom exception class for handling exceptions related to comments in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
public class CommentException extends RuntimeException {

    // Default constructor
    public CommentException() {
    }

    // Constructor with message
    public CommentException(String message) {
        super(message);
    }
}
