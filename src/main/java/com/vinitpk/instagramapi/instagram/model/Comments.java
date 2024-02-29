package com.vinitpk.instagramapi.instagram.model;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing comments in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 09-02-2024
 */
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @NotNull
    @AttributeOverride(name="id", column = @Column(name = "user_id"))
    private UserDto userDto;

    @NotNull
    private String content;

    @Embedded
    @ElementCollection
    private Set<UserDto> likedByUsers = new HashSet<UserDto>();

    private LocalDateTime createdAt;

    // Default constructor
    public Comments() {
    }

    // Constructor with parameters
    public Comments(Integer id, UserDto userDto, String content, Set<UserDto> likedByUsers, LocalDateTime createdAt) {
        this.id = id;
        this.userDto = userDto;
        this.content = content;
        this.likedByUsers = likedByUsers;
        this.createdAt = createdAt;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<UserDto> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<UserDto> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString method

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", content='" + content + '\'' +
                ", likedByUsers=" + likedByUsers +
                ", createdAt=" + createdAt +
                '}';
    }
}
