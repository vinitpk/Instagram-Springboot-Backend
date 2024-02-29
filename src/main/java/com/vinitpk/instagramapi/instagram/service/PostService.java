package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.exception.CommentException;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Post;

import java.util.List;

/**
 * Service interface for managing posts.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
public interface PostService {

    /**
     * Create a new post.
     *
     * @param post The post object to create.
     * @param userId The ID of the user creating the post.
     * @return The created post.
     * @throws UserException If the user does not exist.
     */
    Post createPost(Post post, Integer userId) throws UserException;

    /**
     * Delete a post.
     *
     * @param postId The ID of the post to delete.
     * @param userId The ID of the user deleting the post.
     * @return A message indicating the status of the deletion.
     * @throws UserException If the user does not exist.
     * @throws PostException If the post does not exist.
     */
    String deletePost(Integer postId, Integer userId) throws UserException, PostException;

    /**
     * Find posts by user ID.
     *
     * @param userId The ID of the user.
     * @return A list of posts created by the user.
     * @throws UserException If the user does not exist.
     */
    List<Post> findPostByUserId(Integer userId) throws UserException;

    /**
     * Find a post by post ID.
     *
     * @param postId The ID of the post.
     * @return The post with the specified ID.
     * @throws PostException If the post does not exist.
     */
    Post findPostByPostId(Integer postId) throws PostException;

    /**
     * Find all posts.
     *
     * @return A list of all posts.
     * @throws PostException If there is an error retrieving the posts.
     */
    List<Post> findAllPost() throws PostException;

    /**
     * Find all posts by user IDs.
     *
     * @param userIds The IDs of the users.
     * @return A list of posts created by the specified users.
     * @throws PostException If there is an error retrieving the posts.
     * @throws UserException If a user does not exist.
     */
    List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException, UserException;

    /**
     * Save a post.
     *
     * @param postId The ID of the post to save.
     * @param userId The ID of the user saving the post.
     * @return A message indicating the status of the saving operation.
     * @throws PostException If the post does not exist.
     * @throws UserException If the user does not exist.
     */
    String savedPost(Integer postId, Integer userId) throws PostException, UserException;

    /**
     * Unsave a post.
     *
     * @param postId The ID of the post to unsave.
     * @param userId The ID of the user unsaving the post.
     * @return A message indicating the status of the unsaving operation.
     * @throws PostException If the post does not exist.
     * @throws UserException If the user does not exist.
     */
    String unSavedPost(Integer postId, Integer userId) throws PostException, UserException;

    /**
     * Like a post.
     *
     * @param postId The ID of the post to like.
     * @param userId The ID of the user liking the post.
     * @return The liked post.
     * @throws UserException If the user does not exist.
     * @throws PostException If the post does not exist.
     */
    Post likePost(Integer postId, Integer userId) throws UserException, PostException;

    /**
     * Unlike a post.
     *
     * @param postId The ID of the post to unlike.
     * @param userId The ID of the user unliking the post.
     * @return The unliked post.
     * @throws UserException If the user does not exist.
     * @throws PostException If the post does not exist.
     */
    Post unLikePost(Integer postId, Integer userId) throws UserException, PostException;
}
