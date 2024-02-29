package com.vinitpk.instagramapi.instagram.exception;

import java.time.LocalDateTime;

/**
 * Class representing details of an error response.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 09-02-2024
 */
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;

    // Default constructor
    public ErrorDetails() {
    }

    // Parameterized constructor
    public ErrorDetails(String message, String details, LocalDateTime timestamp) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    // Getter and setter methods

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
