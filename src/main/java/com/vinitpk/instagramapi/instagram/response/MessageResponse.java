package com.vinitpk.instagramapi.instagram.response;

/**
 * A simple response model for returning a message.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
public class MessageResponse {

    private String message;

    // Default constructor
    public MessageResponse(){
    }

    // Constructor with message parameter
    public MessageResponse(String message) {
        super();
        this.message = message;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Override toString method to represent the object as a string
    @Override
    public String toString() {
        return "MessageResponse [message=" + message + "]";
    }
}
