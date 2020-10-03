package com.john_deligiannis.laugh_9.bodies;

import org.springframework.web.multipart.MultipartFile;

public class AddPostRequest {

	private MultipartFile multipartFile;
	private String title;
	private String category;
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
}
