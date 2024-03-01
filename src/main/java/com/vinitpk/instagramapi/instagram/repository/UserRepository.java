package com.vinitpk.instagramapi.instagram.repository;

import com.vinitpk.instagramapi.instagram.model.Post;
import com.vinitpk.instagramapi.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing User entities in the database.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 11-02-2024
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    // Method to find a user by email
    Optional<User> findByEmail(String email);

    // Method to find a user by username
    Optional<User> findByUsername(String username);

    // Custom query to find all users by a list of user IDs
    @Query("SELECT u FROM User u WHERE u.id IN :users")
    List<User> findAllUserByUserIds(@Param("users") List<Integer> userIds);

    // Custom query to find users by a search query (username or email)
    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%")
    List<User> findByQuery(@Param("query") String query);

    // Custom query to find all user who saved Post
    @Query("SELECT DISTINCT u FROM User u JOIN u.savedPost p WHERE :targetPost MEMBER OF u.savedPost ")
    List<User> findBySavedPost(@Param("targetPost") Post targetPost);
//    "SELECT DISTINCT u FROM User u JOIN u.savedPost p WHERE :targetPost MEMBER OF u.savedPost"

}
