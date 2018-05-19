package com.personalizednewsrecommendation.common;

import java.util.Map;

public class Massage {
	private Map<String, Object> data;
	private int code;
	private String comment;
	public Map<String, Object> getMassage() {
		return data;
	}
	public void setMassage(Map<String, Object> massage) {
		this.data = massage;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
