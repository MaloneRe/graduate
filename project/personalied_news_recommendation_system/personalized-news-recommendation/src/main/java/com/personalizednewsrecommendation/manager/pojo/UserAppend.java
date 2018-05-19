package com.personalizednewsrecommendation.manager.pojo;

import java.io.*;
import java.util.*;

public class UserAppend implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 4617526468156696554L;

	private User user;
	
	private List<News> newses;
	
	private List<Comment> comments;
	
	public List<News> getNewses() {
		return newses;
	}
	public void setNewses(List<News> newses) {
		this.newses = newses;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
