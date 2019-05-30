package com.example.demo.model;

import java.util.List;

public class UserPageDTO {
	
	private String username;
	private List<Image> images;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
