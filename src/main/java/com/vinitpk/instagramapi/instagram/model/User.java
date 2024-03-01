package com.vinitpk.instagramapi.instagram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinitpk.instagramapi.instagram.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;

/**
 * Entity class representing users in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 07-02-2024
 */
@Entity
@Table(name = "users") // Specifies the table name for this entity in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the user

    private String username;
    private String name;
    private String email;
    private String mobile;
    private String bio;
    private String website;
    private String gender;
    private String image;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; // User's password (JsonProperty used to prevent password serialization)

    @Embedded
    @ElementCollection
    private Set<UserDto> follower = new HashSet<UserDto>(); // Set of users following this user

    @Embedded
    @ElementCollection
    private Set<UserDto> following = new HashSet<UserDto>(); // Set of users whom this user is following

    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<Story>(); // List of stories posted by this user

    @ManyToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> savedPost = new ArrayList<Post>(); // List of posts saved by this user

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(Integer id, String username, String name, String email, String mobile, String bio, String website, String gender, String image, String password, Set<UserDto> follower, Set<UserDto> following, List<Story> stories, List<Post> savedPost) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.bio = bio;
        this.website = website;
        this.gender = gender;
        this.image = image;
        this.password = password;
        this.follower = follower;
        this.following = following;
        this.stories = stories;
        this.savedPost = savedPost;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserDto> getFollower() {
        return follower;
    }

    public void setFollower(Set<UserDto> follower) {
        this.follower = follower;
    }

    public Set<UserDto> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserDto> following) {
        this.following = following;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    // Equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(mobile, user.mobile) && Objects.equals(bio, user.bio) && Objects.equals(website, user.website) && Objects.equals(gender, user.gender) && Objects.equals(image, user.image) && Objects.equals(password, user.password) && Objects.equals(follower, user.follower) && Objects.equals(following, user.following) && Objects.equals(stories, user.stories) && Objects.equals(savedPost, user.savedPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, mobile, bio, website, gender, image, password, follower, following, stories, savedPost);
    }

    // toString method

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", follower=" + follower +
                ", following=" + following +
                ", stories=" + stories +
                ", savedPost=" + savedPost +
                '}';
    }
}
