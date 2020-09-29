package com.john_deligiannis.laugh_9.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="userVote")
public class UserVote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userVoteId;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post post;
	
	@Column(nullable=true)
	private String vote;

	public Long getUserVoteId() {
		return userVoteId;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}

	public String getVote() {
		return vote;
	}

	public void setUserVoteId(Long userVoteId) {
		this.userVoteId = userVoteId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}
	
}
