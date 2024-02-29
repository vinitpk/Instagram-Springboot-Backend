package com.vinitpk.instagramapi.instagram.service;

import com.vinitpk.instagramapi.instagram.exception.CommentException;
import com.vinitpk.instagramapi.instagram.exception.PostException;
import com.vinitpk.instagramapi.instagram.exception.UserException;
import com.vinitpk.instagramapi.instagram.model.Comments;

/**
 * Service interface for managing comments.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
public interface CommentService {

    /**
     * Creates a new comment for a post.
     *
     * @param comment The comment to create
     * @param postId The ID of the post for which the comment is being created
     * @param userId The ID of the user creating the comment
     * @return The created comment
     * @throws UserException If the user does not exist
     * @throws PostException If the post does not exist
     */
    Comments createComment(Comments comment, Integer postId, Integer userId) throws UserException, PostException;

    /**
     * Finds a comment by its ID.
     *
     * @param commentId The ID of the comment to find
     * @return The found comment
     * @throws CommentException If the comment does not exist
     */
    Comments findCommentById(Integer commentId) throws CommentException;

    /**
     * Likes a comment.
     *
     * @param commentId The ID of the comment to like
     * @param userId The ID of the user liking the comment
     * @return The liked comment
     * @throws UserException If the user does not exist
     * @throws CommentException If the comment does not exist
     */
    Comments likeComment(Integer commentId, Integer userId) throws UserException, CommentException;

    /**
     * Removes a like from a comment.
     *
     * @param commentId The ID of the comment to unlike
     * @param userId The ID of the user unliking the comment
     * @return The unliked comment
     * @throws UserException If the user does not exist
     * @throws CommentException If the comment does not exist
     */
    Comments unlikeComment(Integer commentId, Integer userId) throws UserException, CommentException;

    /**
     * Deletes a comment.
     *
     * @param commentId The ID of the comment to delete
     * @return A message indicating the deletion status
     * @throws CommentException If the comment does not exist
     */
    String deleteComment(Integer commentId) throws CommentException;
}
