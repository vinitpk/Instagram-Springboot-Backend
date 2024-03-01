# Instagram Clone API Spring Boot

This project implements an Instagram-like API using Spring Boot. It provides various services for managing users, posts, comments, stories, and more.

This README provides an overview of the project, its features, and instructions for running it locally. It also lists the available services and their functionalities.


## Getting Started

To run this project locally, follow these steps:

1. Clone the repository:

```
git clone https://github.com/vinitpk/Instagram-Springboot-Backend.git

```
2. Navigate to the project directory:

```
cd Instagram-Springboot-Backend
```
3. Build and run the project using Maven:
```
mvn spring-boot:run
```
4. The API will be available at http://localhost:8080.





## Tech Stack
**Server:** Java, Spring Boot, MySQL

**Client:** React, Redux, TailwindCSS

## Features

#### 1. User Service
`registerUser(User user)`: Registers a new user.\
`findUserById(Integer userId)`: Finds a user by their ID.\
`findUserProfile(String token)`: Finds a user profile using a JWT token.\
`findUserByUsername(String userName)`: Finds a user by their username.\
`followUser(Integer reqUserId, Integer followUserId)`: Follows a user.\
`unfollowUser(Integer reqUserId, Integer followUserId)`: Unfollows a user.\
`findUsersByUserIds(List<Integer> userIds)`: Finds users by their IDs.\
`searchUser(String query)`: Searches for users based on a query.
updateUserDetails(User existingUser, User updatedUser): Updates user details.

#### 2. Post Service
`createPost(Post post, Integer userId)`: Creates a new post for a user.\
`deletePost(Integer postId, Integer userId)`: Deletes a post.\
`findPostByUserId(Integer userId)`: Finds posts by user ID.\
`findPostByPostId(Integer postId)`: Finds a post by its ID.\
`findAllPost()`: Finds all posts.\
`findAllPostByUserId(List<Integer> userIds)`: Finds all posts by a list of user IDs.\
`savedPost(Integer postId, Integer userId)`: Saves a post for a user.\
`unSavedPost(Integer postId, Integer userId)`: Un-saves a post for a user.\
`likePost(Integer postId, Integer userId)`: Likes a post.\
`unLikePost(Integer postId, Integer userId)`: Un-likes a post.

#### 3. Comment Service
`createComment(Comments comment, Integer postId, Integer userId)`: Creates a new comment on a post.\
`findCommentById(Integer commentId)`: Finds a comment by its ID.\
`likeComment(Integer commentId, Integer userId)`: Likes a comment.\
`unlikeComment(Integer commentId, Integer userId)`: Un-likes a comment.\
`deleteComment(Integer commentId)`: Deletes a comment.

#### 4. Story Service
`createStory(Story story, Integer userId)`: Creates a new story for a user.\
`findStoryByUserId(Integer userId)`: Finds stories by user ID.

## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Authors

- [@vinitpk](https://www.github.com/vinitpk)



