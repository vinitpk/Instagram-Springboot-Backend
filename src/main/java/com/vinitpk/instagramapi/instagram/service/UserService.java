package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.User;

import java.util.List;

/**
 * Interface defining operations related to user management.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 09-02-2024
 */
public interface UserService {

    /**
     * Registers a new user.
     *
     * @param user The user to register
     * @return The registered user
     * @throws UserException If there is an issue registering the user
     */
    User registerUser(User user) throws UserException;

    /**
     * Finds a user by their ID.
     *
     * @param userId The ID of the user to find
     * @return The found user
     * @throws UserException If the user is not found
     */
    User findUserById(Integer userId) throws UserException;

    /**
     * Finds the profile of the user associated with the given token.
     *
     * @param token The authentication token
     * @return The user's profile
     * @throws UserException If the user's profile cannot be found
     */
    User findUserProfile(String token) throws UserException;

    /**
     * Finds a user by their username.
     *
     * @param userName The username of the user to find
     * @return The found user
     * @throws UserException If the user is not found
     */
    User findUserByUsername(String userName) throws UserException;

    /**
     * Allows a user to follow another user.
     *
     * @param reqUserId     The ID of the user requesting to follow
     * @param followUserId  The ID of the user to be followed
     * @return A message indicating the success of the operation
     * @throws UserException If there is an issue following the user
     */
    String followUser(Integer reqUserId, Integer followUserId) throws UserException;

    /**
     * Allows a user to unfollow another user.
     *
     * @param reqUserId     The ID of the user requesting to unfollow
     * @param followUserId  The ID of the user to be unfollowed
     * @return A message indicating the success of the operation
     * @throws UserException If there is an issue unfollowing the user
     */
    String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException;

    /**
     * Finds users based on their IDs.
     *
     * @param userIds The list of user IDs
     * @return A list of users
     * @throws UserException If there is an issue finding users
     */
    List<User> findUsersByUserIds(List<Integer> userIds) throws UserException;

    /**
     * Searches for users based on a query string.
     *
     * @param query The search query
     * @return A list of users matching the query
     * @throws UserException If there is an issue searching for users
     */
    List<User> searchUser(String query) throws UserException;

    /**
     * Updates the details of a user.
     *
     * @param updatedUser   The updated user details
     * @param existingUser  The existing user
     * @return The updated user
     * @throws UserException If there is an issue updating the user details
     */
    User updateUserDetails(User updatedUser, User existingUser) throws UserException;
}
