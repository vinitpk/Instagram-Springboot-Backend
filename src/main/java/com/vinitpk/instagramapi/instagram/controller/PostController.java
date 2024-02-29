package com.vinitpk.instagramapi.instagram.controller;

import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Post;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.response.MessageResponse;
import com.vinitpk.instagramapi.instagram.service.PostService;
import com.vinitpk.instagramapi.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to posts.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Endpoint to create a post
    @PostMapping("/create")
    public ResponseEntity<Post> createPostHandler(@RequestBody Post post, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        Post createdPost = postService.createPost(post, user.getId());
        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }

    // Endpoint to find all posts by a specific user
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Post>> findPostByUserIdsHandler(@PathVariable("userId") Integer userId) throws UserException {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    // Endpoint to find all posts by users followed by a specific user
    @GetMapping("/following/{userIds}")
    public ResponseEntity<List<Post>> findAllPostByUserIdsHandler(@PathVariable("userIds") List<Integer> userIds) throws UserException, PostException {
        List<Post> posts = postService.findAllPostByUserId(userIds);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    // Endpoint to find a post by its ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Integer postId) throws PostException {
        Post post = postService.findPostByPostId(postId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    // Endpoint to like a post
    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Post post = postService.likePost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    // Endpoint to unlike a post
    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unlikePostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Post post = postService.unLikePost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    // Endpoint to delete a post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        String message = postService.deletePost(postId, user.getId());
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    // Endpoint to save a post
    @PutMapping("/save-post/{postId}")
    public ResponseEntity<MessageResponse> savePostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        String message = postService.savedPost(postId, user.getId());
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    // Endpoint to unsave a post
    @PutMapping("/unsave-post/{postId}")
    public ResponseEntity<MessageResponse> unSavePostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        String message = postService.unSavedPost(postId, user.getId());
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
