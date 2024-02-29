package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.exception.StoryException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Story;
import com.vinitpk.instagramapi.instagram.model.User;

import java.util.List;

/**
 * Service interface for managing stories.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
public interface StoryService {

    /**
     * Creates a new story for the given user.
     *
     * @param story  The story to create
     * @param userId The ID of the user who owns the story
     * @return The created story
     * @throws UserException If the user is not found
     */
    Story createStory(Story story, Integer userId) throws UserException;

    /**
     * Finds all stories belonging to the specified user.
     *
     * @param userId The ID of the user
     * @return A list of stories belonging to the user
     * @throws UserException   If the user is not found
     * @throws StoryException If there is an issue retrieving stories
     */
    List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;

}
