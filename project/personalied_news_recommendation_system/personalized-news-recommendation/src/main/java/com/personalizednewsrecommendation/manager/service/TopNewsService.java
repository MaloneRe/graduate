package com.personalizednewsrecommendation.manager.service;

import java.util.List;

import com.personalizednewsrecommendation.manager.pojo.*;

public interface TopNewsService {
	
	public void saveTodayNews(TopNews topNews);
	
	public List<News> getTodayNews();
}
