package com.example.demo.model;

import com.example.demo.ImageBytesUtil;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
	private String token;
	private String title;
	private LocalDate createDate;
	private byte[] data;
	private byte[] thumbData;
	private String format;
	private int width;
	private int height;
	private int thumbWidth;
	private int thumbHeight;




	public Image(String token, String title, LocalDate createDate, BufferedImage source, BufferedImage thumbnail, String format) {
		this.token = token;
		this.title = title;
		this.createDate = createDate;
		this.data = ImageBytesUtil.imageToBytes(source);
		this.thumbData = ImageBytesUtil.imageToBytes(thumbnail);
		this.format = format;
		this.width = source.getWidth();
		this.height = source.getHeight();
		this.thumbWidth = thumbnail.getWidth();
		this.thumbHeight = thumbnail.getHeight();
	}

	public Image() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getThumbData() {
		return thumbData;
	}

	public void setThumbData(byte[] thumbData) {
		this.thumbData = thumbData;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getThumbWidth() {
		return thumbWidth;
	}

	public void setThumbWidth(int thumbWidth) {
		this.thumbWidth = thumbWidth;
	}

	public int getThumbHeight() {
		return thumbHeight;
	}

	public void setThumbHeight(int thumbHeight) {
		this.thumbHeight = thumbHeight;
	}

}
