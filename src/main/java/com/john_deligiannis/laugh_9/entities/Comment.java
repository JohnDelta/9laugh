package com.john_deligiannis.laugh_9.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long commentId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="postId")
	private Post post;
	
	@Column(nullable=false)
	private String comment;
	
	@Column(nullable=false)
	private Long date;

	public Long getCommentId() {
		return commentId;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}

	public String getComment() {
		return comment;
	}

	public Long getDate() {
		return date;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(Long date) {
		this.date = date;
	}
	
}
