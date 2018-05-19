package com.personalizednewsrecommendation.manager.log;

import java.util.regex.*;

public class UrlMatcher {
	private static final String REGX_USER = "/api/users/?\\w*";
	private static final String REGX_CLICK = "/api/news/\\d+/content/??";
	private static final String REGX_COMMENT = "/api/\\d+/comment/??";
	private static final String REGX_BEHAVIOR= "/api/\\d+/??";
	
	public static Pattern getRegxUser(){
		return Pattern.compile(REGX_USER);
	}
	
	public static Pattern getRegxClick(){
		return Pattern.compile(REGX_CLICK);
	}
	
	public static Pattern getRegxComment(){
		return Pattern.compile(REGX_COMMENT);
	}
	
	public static Pattern getRegxBehavior(){
		return Pattern.compile(REGX_BEHAVIOR);
	}
}
