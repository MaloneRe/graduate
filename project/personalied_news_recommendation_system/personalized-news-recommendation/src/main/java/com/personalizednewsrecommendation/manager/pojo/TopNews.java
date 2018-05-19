package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;
import java.util.List;

public class TopNews implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 986975736954835341L;
	
	private String id;
	private List<News> newses;
	
	private String content;

	
	
	public TopNews(String id, List<News> newses) {
		super();
		this.id = id;
		this.newses = newses;
	}

	public TopNews(List<News> newses) {
		super();
		this.newses = newses;
	}

	public TopNews(List<News> newses, String content) {
		super();
		this.newses = newses;
		this.content = content;
	}

	public TopNews(String id, List<News> newses, String content) {
		super();
		this.id = id;
		this.newses = newses;
		this.content = content;
	}

	public TopNews() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<News> getNewses() {
		return newses;
	}

	public void setNewses(List<News> newses) {
		this.newses = newses;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
