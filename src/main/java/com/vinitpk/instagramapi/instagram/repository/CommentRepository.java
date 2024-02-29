package com.vinitpk.instagramapi.instagram.repository;

import com.vinitpk.instagramapi.instagram.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Vinit Kelginmane
 * @project: instagram-api-springboot
 * @Date: 14-02-2024
 */
@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
}
