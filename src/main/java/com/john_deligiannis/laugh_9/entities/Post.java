package com.john_deligiannis.laugh_9.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer postId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(nullable=false)
	private String category;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private String mediaSource;
	
	@Column(nullable=true)
	private Integer upvotes;
	
	@Column(nullable=true)
	private Integer downvotes;
	
	public Integer getPostId() {
		return postId;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getMediaSource() {
		return mediaSource;
	}
	
	public Integer getUpvotes() {
		return upvotes;
	}
	
	public Integer getDownvotes() {
		return downvotes;
	}
	
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}
	
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}
	
	public void setDownvotes(Integer downvotes) {
		this.downvotes = downvotes;
	}
	
}
