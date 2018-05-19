package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;

public class Category implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = -8915052111426488194L;
	private Long id;
	private String name;
	private String time;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public java.lang.String toString() {
		
		return id+","+name+","+time;
	}
}
