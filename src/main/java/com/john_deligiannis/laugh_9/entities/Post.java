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
	private Long postId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(nullable=false)
	private String category;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=true)
	private String popularity;
	
	@Column(nullable=false)
	private String mediaSource;
	
	@Column(nullable=true)
	private Long upvotes;
	
	@Column(nullable=true)
	private Long downvotes;
	
	@Column(nullable=false)
	private Long date;
	
	public Long getPostId() {
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
	
	public String getPopularity() {
		return popularity;
	}
	
	public String getMediaSource() {
		return mediaSource;
	}
	
	public Long getUpvotes() {
		return upvotes;
	}
	
	public Long getDownvotes() {
		return downvotes;
	}
	
	public Long getDate() {
		return date;
	}
	
	public void setPostId(Long postId) {
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
	
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}
	
	public void setUpvotes(Long upvotes) {
		this.upvotes = upvotes;
	}
	
	public void setDownvotes(Long downvotes) {
		this.downvotes = downvotes;
	}
	
	public void setDate(Long date) {
		this.date = date;
	}
	
}
