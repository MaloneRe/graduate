package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 8006518982645284263L;
	private Long id;
	private String name;
	private String password;
	private String time;
	
	public User(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public User() {
		super();
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	*/
	
}
