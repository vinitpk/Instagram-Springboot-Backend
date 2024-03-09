package com.vinitpk.instagramapi.instagram.exception;

/**
 * Custom exception class for handling exceptions related to stories in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
public class StoryException extends RuntimeException {

    // Default constructor
    public StoryException() {
    }

    // Constructor with message
    public StoryException(String message) {
        super(message);
    }
}
