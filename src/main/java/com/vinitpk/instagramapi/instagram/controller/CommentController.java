package com.vinitpk.instagramapi.instagram.controller;

import com.vinitpk.instagramapi.instagram.exception.CommentException;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Comments;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.service.CommentService;
import com.vinitpk.instagramapi.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to handle operations related to comments.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // Endpoint to create a comment for a specific post
    @PostMapping("/create/{postId}")
    public ResponseEntity<Comments> createCommentHandler(@PathVariable("postId") Integer postId, @RequestBody Comments comment, @RequestHeader("Authorization") String token) throws UserException, PostException {
        // Find user profile using token
        User user = userService.findUserProfile(token);
        // Create the comment and return the response with the created comment and HTTP status code 201 (Created)
        Comments createdComment = commentService.createComment(comment, postId, user.getId());
        return new ResponseEntity<Comments>(createdComment, HttpStatus.CREATED);
    }

    // Endpoint to like a comment
    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comments> likeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String token) throws UserException, CommentException {
        // Find user profile using token
        User user = userService.findUserProfile(token);
        // Like the comment and return the response with the liked comment and HTTP status code 200 (OK)
        Comments likeComment = commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<Comments>(likeComment, HttpStatus.OK);
    }

    // Endpoint to unlike a comment
    @PutMapping("/unlike/{commentId}")
    public ResponseEntity<Comments> unlikeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String token) throws UserException, CommentException {
        // Find user profile using token
        User user = userService.findUserProfile(token);
        // Unlike the comment and return the response with the unliked comment and HTTP status code 200 (OK)
        Comments unlikeComment = commentService.unlikeComment(commentId, user.getId());
        return new ResponseEntity<Comments>(unlikeComment, HttpStatus.OK);
    }
}
