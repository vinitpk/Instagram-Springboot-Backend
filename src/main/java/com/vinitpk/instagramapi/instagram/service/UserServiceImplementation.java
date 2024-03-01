package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.repository.UserRepository;
import com.vinitpk.instagramapi.instagram.security.JwtTokenClaims;
import com.vinitpk.instagramapi.instagram.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service implementation for user-related operations.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Registers a new user.
     *
     * @param user The user to register
     * @return The registered user
     * @throws UserException If the email or username is already in use, or if required fields are missing
     */
    @Override
    public User registerUser(User user) throws UserException {
        // Check if the email already exists
        Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist.isPresent()) {
            throw new UserException("Email is already in use");
        }

        // Check if the username already exists
        Optional<User> isUserNameExist = userRepository.findByUsername(user.getUsername());
        if (isUserNameExist.isPresent()) {
            throw new UserException("Username is already taken");
        }

        // Check if all required fields are present
        if (user.getEmail() == null || user.getPassword() == null || user.getUsername() == null || user.getName() == null) {
            throw new UserException("All fields are required");
        }

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        return userRepository.save(user);
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId The ID of the user to find
     * @return The user with the given ID
     * @throws UserException If no user is found with the specified ID
     */
    @Override
    public User findUserById(Integer userId) throws UserException {
        // Attempt to find the user by ID in the repository
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }

        // Throw an exception if no user is found with the specified ID
        throw new UserException("User not found with ID: " + userId);
    }

    /**
     * Finds the user profile associated with a JWT token.
     *
     * @param token The JWT token
     * @return The user profile
     * @throws UserException If the token is invalid or no user is found
     */
    @Override
    public User findUserProfile(String token) throws UserException {
        // Extract the username from the JWT token
        token = token.substring(7);
        JwtTokenClaims jwtTokenClaims = jwtTokenProvider.getClaimsFromToken(token);
        String email = jwtTokenClaims.getUsername();

        // Attempt to find the user by email in the repository
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        // Throw an exception if no user is found for the given token
        throw new UserException("Invalid token");
    }

    /**
     * Finds a user by their username.
     *
     * @param userName The username of the user to find
     * @return The user with the specified username
     * @throws UserException If no user is found with the specified username
     */
    @Override
    public User findUserByUsername(String userName) throws UserException {
        // Attempt to find the user by username in the repository
        Optional<User> user = userRepository.findByUsername(userName);
        if (user.isPresent()) {
            return user.get();
        }

        // Throw an exception if no user is found with the specified username
        throw new UserException("User not found with username: " + userName);
    }

    /**
     * Follows another user.
     *
     * @param reqUserId      The ID of the user requesting to follow
     * @param followUserId   The ID of the user to follow
     * @return A message indicating the follow action
     * @throws UserException If there is an issue with following the user
     */
    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();

        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        follower.setUserImage(reqUser.getImage());

        UserDto following = new UserDto();

        following.setEmail(followUser.getEmail());
        following.setId(followUser.getId());
        following.setName(followUser.getName());
        following.setUsername(followUser.getUsername());
        following.setUserImage(followUser.getImage());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "You are following " + followUser.getUsername();
    }

    /**
     * Unfollows another user.
     *
     * @param reqUserId        The ID of the user requesting to unfollow
     * @param unfollowUserId   The ID of the user to unfollow
     * @return A message indicating the unfollow action
     * @throws UserException If there is an issue with unfollowing the user
     */
    @Override
    public String unfollowUser(Integer reqUserId, Integer unfollowUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User unfollowUser = findUserById(unfollowUserId);

        UserDto following = new UserDto();

        following.setEmail(unfollowUser.getEmail());
        following.setId(unfollowUser.getId());
        following.setName(unfollowUser.getName());
        following.setUsername(unfollowUser.getUsername());
        following.setUserImage(unfollowUser.getImage());

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        follower.setUserImage(reqUser.getImage());


        reqUser.getFollowing().remove(following);
        unfollowUser.getFollower().remove(follower);

        userRepository.save(reqUser);
        userRepository.save(unfollowUser);

        return "You have unfollowed " + unfollowUser.getUsername();
    }


    /**
     * Finds users by their IDs.
     *
     * @param userIds The list of user IDs to find
     * @return The list of users with the specified IDs
     * @throws UserException If there is an issue with finding the users
     */
    @Override
    public List<User> findUsersByUserIds(List<Integer> userIds) throws UserException {
        List<User> users = userRepository.findAllUserByUserIds(userIds);
        return users;
    }

    /**
     * Searches for users based on a query string.
     *
     * @param query The query string to search for
     * @return The list of users matching the query
     * @throws UserException If no users are found matching the query
     */
    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if(users.isEmpty()){
            throw new UserException("user not found");
        }
        return users;
    }

    /**
     * Updates user details.
     *
     * @param existingUser The existing user details
     * @param updatedUser  The updated user details
     * @return The updated user
     * @throws UserException If there is an issue with updating the user details
     */
    @Override
    public User updateUserDetails(User existingUser, User updatedUser) throws UserException {
        if (!existingUser.getId().equals(updatedUser.getId())) {
            throw new UserException("You can't update another user.");
        }
        if (updatedUser.getUsername() != null) {
            if (!existingUser.getUsername().equals(updatedUser.getUsername())) {
                // Check if the new username is already taken
                Optional<User> duplicateUser = userRepository.findByUsername(updatedUser.getUsername());
                if (duplicateUser.isPresent()) {
                    throw new UserException("Username is already taken. Choose a different username.");
                }
                existingUser.setUsername(updatedUser.getUsername());
            }
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getBio() != null) {
            existingUser.setBio(updatedUser.getBio());
        }
        if (updatedUser.getMobile() != null) {
            existingUser.setMobile(updatedUser.getMobile());
        }
        if (updatedUser.getGender() != null) {
            existingUser.setGender(updatedUser.getGender());
        }
        if (updatedUser.getWebsite() != null) {
            existingUser.setWebsite(updatedUser.getWebsite());
        }
        if (updatedUser.getImage() != null) {
            existingUser.setImage(updatedUser.getImage());
        }

        return userRepository.save(existingUser);
    }


}
