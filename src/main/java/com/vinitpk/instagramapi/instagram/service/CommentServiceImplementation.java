package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import com.vinitpk.instagramapi.instagram.exception.CommentException;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Comments;
import com.vinitpk.instagramapi.instagram.model.Post;
import com.vinitpk.instagramapi.instagram.model.User;
import com.vinitpk.instagramapi.instagram.repository.CommentRepository;
import com.vinitpk.instagramapi.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service implementation for managing comments.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comments createComment(Comments comment, Integer postId, Integer userId) throws UserException, PostException {
        // Find user by ID
        User user = userService.findUserById(userId);
        // Find post by ID
        Post post = postService.findPostByPostId(postId);

        // Create a UserDto object from the user
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserImage(user.getUsername()); // Assuming username is used as the user image
        userDto.setUserImage(user.getImage()); // Set user image

        // Set userDto and createdAt fields of the comment
        comment.setUserDto(userDto);
        comment.setCreatedAt(LocalDateTime.now());

        // Save the comment
        Comments createdComment = commentRepository.save(comment);

        // Add the comment to the post's comments list
        post.getComments().add(createdComment);
        postRepository.save(post);

        return createdComment;
    }

    @Override
    public Comments findCommentById(Integer commentId) throws CommentException {
        // Find comment by ID
        Optional<Comments> optionalComment = commentRepository.findById(commentId);

        // Check if the comment exists
        if(optionalComment.isPresent()){
            return optionalComment.get();
        }

        // Throw an exception if the comment does not exist
        throw new CommentException("Comment does not exist with comment id: " + commentId);
    }

    @Override
    public Comments likeComment(Integer commentId, Integer userId) throws UserException, CommentException {
        // Find user by ID
        User user = userService.findUserById(userId);
        // Find comment by ID
        Comments comment = findCommentById(commentId);

        // Create a UserDto object from the user
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserImage(user.getUsername()); // Assuming username is used as the user image
        userDto.setUserImage(user.getImage()); // Set user image

        // Add the userDto to the likedByUsers set of the comment
        comment.getLikedByUsers().add(userDto);

        return commentRepository.save(comment);
    }

    @Override
    public Comments unlikeComment(Integer commentId, Integer userId) throws UserException, CommentException {
        // Find user by ID
        User user = userService.findUserById(userId);
        // Find comment by ID
        Comments comment = findCommentById(commentId);

        // Create a UserDto object from the user
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setUserImage(user.getUsername()); // Assuming username is used as the user image
        userDto.setUserImage(user.getImage()); // Set user image

        // Remove the userDto from the likedByUsers set of the comment
        comment.getLikedByUsers().remove(userDto);

        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(Integer commentId) throws CommentException {
        // Check if the comment exists
        if(commentRepository.existsById(commentId)){
            // Delete the comment if it exists
            commentRepository.deleteById(commentId);
            return "Comment deleted successfully";
        }
        // Throw an exception if the comment does not exist
        throw new CommentException("Cannot delete comment with id: " + commentId + ". Comment does not exist.");
    }
}
