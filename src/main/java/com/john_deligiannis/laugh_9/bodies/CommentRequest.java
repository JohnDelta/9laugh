package com.john_deligiannis.laugh_9.bodies;

public class CommentRequest {
	
	private long postId;
	private String comment;
	
	public long getPostId() {
		return postId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
