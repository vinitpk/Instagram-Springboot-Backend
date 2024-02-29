package com.vinitpk.instagramapi.instagram.model;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import jakarta.persistence.*;
        import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity class representing posts in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 07-02-2024
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;

    @Column(nullable = false)
    private String image;
    private String location;
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "id", column = @Column(name = "user_id")),
                    @AttributeOverride(name = "email", column = @Column(name = "user_email")),
                    @AttributeOverride(name = "username", column = @Column(name = "user_username"))
            }
    )
    private UserDto user;

    @OneToMany
    private List<Comments> comments = new ArrayList<Comments>();

    @Embedded
    @ElementCollection
    @JoinTable(name = "likedByUsers", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDto> likedByUsers = new HashSet<UserDto>();

    // Default constructor
    public Post() {}

    // Constructor with parameters
    public Post(Integer id, String caption, String image, String location,
                LocalDateTime createdAt, UserDto user, List<Comments> comments,
                Set<UserDto> likedByUsers) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.location = location;
        this.createdAt = createdAt;
        this.user = user;
        this.comments = comments;
        this.likedByUsers = likedByUsers;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Set<UserDto> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<UserDto> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }


    // toString method

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", image='" + image + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", comments=" + comments +
                ", likedByUsers=" + likedByUsers +
                '}';
    }
}
