# 9laugh_api

Restfull API provider for the 9laugh project

9laugh's front-end web app can be found at https://github.com/JohnDelta/9laugh_webapp

## About
9laugh is an entertaining web platform in which users upload and share content. Other users can see the content and comment/like it. The content can be created by the users themselves or by other external sources.

# Build Info

## Spring / Spring boot project

## Requirements
- JDK 1.8+
- Maven 3.8+
- Currently using Spring Tool Suit for Eclipse IDE

## Spring dependencies
- Spring Web Starter
- Spring Data JPA
- MySQL Driver
- json web token
- spring boot starter security
- spring boot starter thymeleaf

## Setup the MySQL Database (User: 9laughUser, Password: 9laughPassword)
- Server: localhost:3306, change it from src/main/resources/application.properties if you use different
- Login to MySQL command line

` create database 9laugh; `

- Create the database user and set their password

` create user '9laughUser'@'%' identified by '9laughPassword'; `

- Give user the necessary privileges

` grant all on 9laugh.* to '9laughUser'@'%'; `

## Install API (setup DB first required)
- clone the project to a location and open it

` git clone https://github.com/JohnDelta/9laugh_api.git `

` cd 9laugh_api `

- build and run the project locally (hosted on localhost:8082/)

` mvn spring-boot:run `
	
# API Requests (local testing server: localhost:8082)

## add user (create account)
- url: localhost:8082/api/user/add
- method: POST
- content-type: multipart/form-data
- body: (in form data) {file: imgFile, username: "", password: ""}
- returns: application/json
- comments: image file is optional. If no image file is attached the default image will be used

## delete user (remove account)
- url: localhost:8082/api/user/delete
- method: POST
- content-type: application/json
- body: {}
- header: {Authorization: (jwtToken)}
- returns: application/json

## get user data (token's owner data)
- url: localhost:8082/api/user/get
- method: POST
- content-type: application/json
- body: {}
- header: {Authorization: (jwtToken)}
- returns: application/json

## authenticate user (account login)
- url: localhost:8082/api/authenticate
- method: POST
- content-type: application/json
- body: {username: "", password: ""}
- returns: application/json

## Add post
- url: localhost:8082/api/post/add
- method: POST
- content-type: mutlipart/form-data
- body: (in form data) {file: imgFile, title: "", category: ""}
- header: {Authorization: (jwtToken)}
- returns: application/json
- comments: image file is optional. If no image file is attached the default image will be used

## delete post
url: localhost:8082/api/post/delete
- method: POST
- content-type: application/json
- body: {"postId": (id)}
- header: {Authorization: (jwtToken)}
- returns: application/json

## get popular posts
- url: localhost:8082/api/post/get/popular
- method: POST
- content-type: application/json
- body: {category: "funny" | "news" | "random" | "wtf"}
- returns: application/json

## get new posts
- url: localhost:8082/api/post/get/new
- method: POST
- content-type: application/json
- body: {category: "funny" | "news" | "random" | "wtf"}
- returns: application/json

## get post by id
- url: localhost:8082/api/post/get
- method: POST
- content-type: application/json
- body: {"postId": (id)}
- returns: application/json

## get posts by user id
- url: localhost:8082/api/post/get/user
- method: POST
- content-type: application/json
- body: {"username": (username)}
- returns: application/json

## get posts' categories
- url: localhost:8082/api/post/get/categories
- method: POST
- content-type: application/json
- returns: application/json

## user upvotes post
- url: localhost:8082/api/post/upvote
- method: POST
- content-type: application/json
- body: {"postId": (id)}
- header: {Authorization: (jwtToken)}
- returns: application/json

## user downvotes post
- url: localhost:8082/api/post/downvote
- method: POST
- content-type: application/json
- body: {"postId": (id)}
- header: {Authorization: (jwtToken)}
- returns: application/json

## user comment on post
- url: localhost:8082/api/comment/add
- method: POST
- content-type: application/json
- body: {"postId": (id), "comment": (text)}
- header: {Authorization: (jwtToken)}
- returns: application/json

## user remove comment from post
- url: localhost:8082/api/comment/delete
- method: POST
- content-type: application/json
- body: {"commentId": (id)}
- header: {Authorization: (jwtToken)}
- returns: application/json

## get post's comments
- url: localhost:8082/api/comment/get/all
- method: POST
- content-type: application/json
- body: {}
- header: {}
- returns: application/json

# Other concept info

## Entities
- user (id, username, password)
- post (id, userId(uploader), category, title, popularity, mediaSource, upvotes, downvotes)
- userVote (id, userId, postId, vote) where vote contains (upvote, downvote, null)
- comments (id, userId, postId, comment, date)










