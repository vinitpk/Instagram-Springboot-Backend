package com.vinitpk.instagramapi.instagram.controller;

import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.response.MessageResponse;
import com.vinitpk.instagramapi.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to users.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to find a user by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable("id") Integer id) throws UserException {
        User user = userService.findUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Endpoint to find a user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUserNameHandler(@PathVariable("username") String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    // Endpoint to follow a user
    @PutMapping("/follow/{followUserId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable("followUserId") Integer followUserId, @RequestHeader("Authorization") String token) throws UserException {
        User reqUser = userService.findUserProfile(token);
        String message = userService.followUser(reqUser.getId(), followUserId);
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    }

    // Endpoint to unfollow a user
    @PutMapping("/unfollow/{unFollowUserId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable("unFollowUserId") Integer unfollowUserId, @RequestHeader("Authorization") String token) throws UserException {
        User reqUser = userService.findUserProfile(token);
        String message = userService.unfollowUser(reqUser.getId(), unfollowUserId);
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    }

    // Endpoint to find the user profile
    @GetMapping("/req")
    public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    // Endpoint to find users by their IDs
    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException {
        List<User> users = userService.findUsersByUserIds(userIds);
        return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
    }

    // Endpoint to search for users by query
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // Endpoint to edit user account
    @PutMapping("/account/edit")
    public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token, @RequestBody User updatedUser) throws UserException {
        User reqUser = userService.findUserProfile(token);
        User user = userService.updateUserDetails(reqUser, updatedUser);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
