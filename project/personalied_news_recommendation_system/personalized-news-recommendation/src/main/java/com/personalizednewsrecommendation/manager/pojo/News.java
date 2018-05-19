package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;

public class News implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = -3580585289506912508L;
	private Long id;
	private String title;
	private String url;
	private String imageUrl;
	private String media;
	private String content;
	private Long categoryId;
	private String time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public java.lang.String toString() {
		// TODO Auto-generated method stub
		return id+","+title+","+url
				+","+imageUrl+","+content
				+","+media+","+time+","
				+categoryId;
	}
}
