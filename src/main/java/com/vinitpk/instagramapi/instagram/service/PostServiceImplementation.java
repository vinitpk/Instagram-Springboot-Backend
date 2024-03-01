package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Post;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.repository.PostRepository;
import com.vinitpk.instagramapi.instagram.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PostService interface.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Method to create a new post
    @Override
    public Post createPost(Post post, Integer userid) throws UserException {

        User user = userService.findUserById(userid);
        // Convert user to DTO
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        // Set user and creation time for the post
        post.setUser(userDto);
        post.setCreatedAt(LocalDateTime.now());
        // Save the post
        Post createdPost = postRepository.save(post);
        return createdPost;
    }

    // Method to delete a post
    @Transactional
    @Override
    public String deletePost(Integer postId, Integer userId) throws UserException, PostException {

        // Find the post by its ID
        Post post = findPostByPostId(postId);

        // Find the user by ID
        User user = userService.findUserById(userId);

        // Get all users who saved post
        List<User> postSavedUsers = userRepository.findBySavedPost(post);

        // Check if the user is the owner of the post
        if(post.getUser().getId().equals(user.getId())){

            // Remove savedPost one by one from user
            for(User u: postSavedUsers){
                u.getSavedPost().remove(post);
                userRepository.save(u);
            }

            // Delete the post
            postRepository.deleteById(postId);
            return "Post deleted successfully";
        }
        throw new PostException("You don't have access to delete this post.");
    }

    // Method to find posts by user ID
    @Override
    public List<Post> findPostByUserId(Integer userId) throws UserException {

        // Find posts by user ID
        List<Post> posts = postRepository.findByUserId(userId);

        // If no posts are found, throw an exception
        if(posts.isEmpty()){
            throw new UserException("This user doesn't have any post...");
        }
        return posts;
    }

    // Method to find a post by its ID
    @Override
    public Post findPostByPostId(Integer postId) throws PostException {
        // Find the post by its ID
        Optional<Post> optionalPost = postRepository.findById(postId);

        // If the post is present, return it; otherwise, throw an exception
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }
        throw new PostException("Post not found with id : " + postId );
    }

    // Method to find all posts
    @Override
    public List<Post> findAllPost() throws PostException {
        // Find all posts
        List<Post> posts = postRepository.findAll();
        // If no posts are found, throw an exception
        if(posts.size()>0) {
            return posts;
        }
        throw new PostException("Post Not Exist");
    }

    // Method to find all posts by a list of user IDs
    @Override
    public List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException, UserException {

        // Find all posts by user IDs
        List<Post> posts = postRepository.findAllPostByUserIds(userIds);
        // If no posts are found, throw an exception
        if(posts.isEmpty()){
            throw new PostException("No Post available");
        }
        return posts;
    }

    // Method to save a post
    @Override
    public String savedPost(Integer postId, Integer userId) throws PostException, UserException {

        // Find the post by its ID
        Post post = findPostByPostId(postId);
        // Find the user by ID
        User user = userService.findUserById(userId);
        // If the post is not already saved by the user, save it
        if(!user.getSavedPost().contains(post)){
            user.getSavedPost().add(post);
            userRepository.save(user);
        }
        return "Post saved successfully";
    }

    // Method to unsave a post
    @Override
    public String unSavedPost(Integer postId, Integer userId) throws PostException, UserException {
        // Find the post by its ID
        Post post = findPostByPostId(postId);
        // Find the user by ID
        User user = userService.findUserById(userId);
        // If the post is saved by the user, remove it
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
            userRepository.save(user);
        }
        return "Post removed successfully";
    }

    // Method to like a post
    @Override
    public Post likePost(Integer postId, Integer userId) throws UserException, PostException {
        // Find the post by its ID
        Post post = findPostByPostId(postId);
        // Find the user by ID
        User user = userService.findUserById(userId);

        // Convert user to DTO
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setUserImage(user.getImage());

        // Add the user DTO to the list of users who liked the post
        post.getLikedByUsers().add(userDTO);
        return postRepository.save(post);
    }

    // Method to unlike a post
    @Override
    public Post unLikePost(Integer postId, Integer userId) throws UserException, PostException {
        // Find the post by its ID
        Post post = findPostByPostId(postId);
        // Find the user by ID
        User user = userService.findUserById(userId);

        // Convert user to DTO
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());

        // Remove the user DTO from the list of users who liked the post
        post.getLikedByUsers().remove(userDto);

        return postRepository.save(post);
    }
}
