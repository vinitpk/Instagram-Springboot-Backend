package com.vinitpk.instagramapi.instagram.model;

import com.vinitpk.instagramapi.instagram.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity class representing stories in the Instagram API.
 *
 * Author: Vinit Kelginmane
 * Project: instagram-api-springboot
 * Date: 07-02-2024
 */
@Entity
@Table(name = "stories") // Specifies the table name for this entity in the database
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the story

    @NotNull
    @Embedded // Specifies that a UserDto object will be embedded within this entity
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")), // Overrides the column name for id
            @AttributeOverride(name = "email", column = @Column(name = "user_email")) // Overrides the column name for email
    })
    private UserDto userDto; // UserDto object representing the user who posted the story

    @NotNull
    private String image; // URL or path to the image associated with the story
    private String caption; // Caption or description for the story
    private LocalDateTime timestamp; // Timestamp indicating when the story was created

    // Default constructor
    public Story() {}

    // Constructor with parameters
    public Story(Integer id, @NotNull UserDto userDto, @NotNull String image, String caption, LocalDateTime timestamp) {
        this.id = id;
        this.userDto = userDto;
        this.image = image;
        this.caption = caption;
        this.timestamp = timestamp;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // toString method

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", image='" + image + '\'' +
                ", caption='" + caption + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
