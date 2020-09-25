# 9laugh

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

## Setup the MySQL Database (User: 9laughUser, Password: 9laughPassword)
Server: localhost:3306, change it from src/main/resources/application.properties if you use different
Login to MySQL command line
	create database 9laugh;
Create the database user and set their password
	create user '9laughUser'@'%' identified by '9laughPassword';
Give user the necessary privileges
	grant all on 9laugh.* to '9laughUser'@'%';
	
# Concept info

## Entities

### User (id, username, password)
- Add new user (user creates account)
- Remove existing user (user deletes their account)

### Post (id, userId(uploader), tag, title, description, src, upvotes, downvotes)
As user you can:
- Add post (user uploads post)
- Remove post (user removes post)
- Get your posts
- Select post (from any user)











