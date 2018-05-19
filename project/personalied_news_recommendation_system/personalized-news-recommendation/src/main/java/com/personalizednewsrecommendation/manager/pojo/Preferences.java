package com.personalizednewsrecommendation.manager.pojo;

import java.io.Serializable;

public class Preferences implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO 
	 *
	 */
	private static final long serialVersionUID = 7997114396619879214L;
	private Long id;
	private Long uid;
	private Long nid;
	private Integer preference;
	private String time;
	
	
	
	public Preferences(Long id, Integer preference) {
		super();
		this.id = id;
		this.preference = preference;
	}
	public Preferences(Long uid, Long nid, Integer preference) {
		super();
		this.uid = uid;
		this.nid = nid;
		this.preference = preference;
	}
	public Preferences() {
		super();
	}
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
	public Integer getPreference() {
		return preference;
	}
	public void setPreference(Integer preference) {
		this.preference = preference;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
