package com.vinitpk.instagramapi.instagram.controller;

import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.StoryException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Story;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.response.MessageResponse;
import com.vinitpk.instagramapi.instagram.service.StoryService;
import com.vinitpk.instagramapi.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to stories.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    // Endpoint to create a story
    @PostMapping("/create")
    public ResponseEntity<Story> createStoryHandler(@RequestBody Story story, @RequestHeader("Authorization") String token) throws UserException {
        // Find user profile using token
        User user = userService.findUserProfile(token);
        // Create the story and return the response with the created story and HTTP status code 201 (Created)
        Story createdStory = storyService.createStory(story, user.getId());
        return new ResponseEntity<Story>(createdStory, HttpStatus.CREATED);
    }

    // Endpoint to find all stories by a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserHandler(@PathVariable("userId") Integer userId) throws UserException, StoryException {
        // Find user by ID
        User user = userService.findUserById(userId);
        // Find all stories by the user and return the response with the list of stories and HTTP status code 200 (OK)
        List<Story> stories = storyService.findStoryByUserId(user.getId());
        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }

    // Endpoint to delete a story
    @DeleteMapping("/delete/{storyId}")
    public ResponseEntity<MessageResponse> deleteStoryHandler(@PathVariable("storyId") Integer storyId, @RequestHeader("Authorization") String token) throws UserException, StoryException {
        User user = userService.findUserProfile(token);
        String message = storyService.deleteStory(storyId, user.getId());
        MessageResponse messageResponse = new MessageResponse(message);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
