package com.vinitpk.instagramapi.instagram.repository;

import com.vinitpk.instagramapi.instagram.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Story entities in the database.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 14-02-2024
 */
@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    // Custom query to find all stories by user ID
    @Query("SELECT s FROM Story s WHERE s.userDto.id = :userId")
    List<Story> findAllStoriesByUserId(@Param("userId") Integer userId);

}
