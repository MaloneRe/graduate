package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;

public class Comment implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 8168569029294915911L;
	private Long id;
	/**
	 * 用户
	 */
	private Long uid;
	/**
	 * 新闻
	 */
	private Long nid;
	
	private String content;
	
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getNid() {
		return nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
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

	@Override
	public java.lang.String toString() {
		// TODO Auto-generated method stub
		return id+","+uid+","+nid+","+content+","+time+";";
	}
	
}
