package com.vinitpk.instagramapi.instagram.repository;

import com.vinitpk.instagramapi.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing Post entities in the database.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 13-02-2024
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Custom query to find posts by user ID
    @Query("select p from Post p where p.user.id=?1")
    List<Post> findByUserId(Integer userId);

    // Custom query to find all posts by a list of user IDs, ordered by creation date
    @Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdAt DESC")
    List<Post> findAllPostByUserIds(@Param("users") List<Integer> userIds);

    // Custom query to find all posts by a list of user IDs, sorted by creation date in descending order
    @Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdAt DESC")
    List<Post> findAllPostByUserIdsSortedByDateDesc(@Param("users") List<Integer> userIds);

}
