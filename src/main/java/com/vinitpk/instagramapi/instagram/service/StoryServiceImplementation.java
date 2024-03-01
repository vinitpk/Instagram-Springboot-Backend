package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.StoryException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Story;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.repository.StoryRepository;
import com.vinitpk.instagramapi.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the StoryService interface.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new story for the specified user.
     *
     * @param story  The story to create
     * @param userId The ID of the user who owns the story
     * @return The created story
     * @throws UserException If the user is not found
     */
    @Override
    public Story createStory(Story story, Integer userId) throws UserException {
        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUserImage(user.getImage());

        story.setUserDto(userDto);
        story.setTimestamp(LocalDateTime.now());
        user.getStories().add(story);

        return storyRepository.save(story);
    }

    /**
     * Finds all stories belonging to the specified user.
     *
     * @param userId The ID of the user
     * @return A list of stories belonging to the user
     * @throws UserException   If the user is not found
     * @throws StoryException If there is an issue retrieving stories
     */
    @Override
    public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
        User user = userService.findUserById(userId);
        List<Story> stories = user.getStories();
        if (stories.isEmpty()) {
            throw new StoryException("This user doesn't have any story");
        }
        return stories;
    }

    // Method to delete a post
    @Override
    public String deleteStory(Integer storyId, Integer userId) throws UserException, StoryException {

        Optional<Story> story = storyRepository.findById(storyId);

        // Find the user by ID
        User user = userService.findUserById(userId);

        if(story.get().getUserDto().getId().equals(user.getId())){

            // Delete the Story
            user.getStories().remove(story.get()); // delete story from user/parent entity
            storyRepository.deleteById(storyId); // delete story from stories/child entity
            return "Story deleted successfully";
        }
        throw new StoryException("You don't have access to delete this Story.");
    }
}
