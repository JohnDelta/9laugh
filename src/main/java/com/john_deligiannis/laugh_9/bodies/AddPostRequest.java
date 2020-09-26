package com.john_deligiannis.laugh_9.bodies;

public class AddPostRequest {
	
	private String category;
	private String title;
	private String description;
	private String mediaSource;
	
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
	
}
