package com.personalizednewsrecommendation.manager.dao;

import java.util.List;

import com.personalizednewsrecommendation.manager.pojo.News;

import org.apache.ibatis.annotations.*;

public interface NewsDAO {
	public void insertNews(News news);
	/**
	 * 
	 * @Method: insertNewses 
	 * @param newses
	 * @throws
	 * 测试失败，问题待解决
	 */
	public void insertNewses(List<News> newses);
	public News getNewsById(Long id);
	public List<News> getNewsesById(Long categoryId);
	public List<News> getNewsesByName(String title);
	public List<News> getAll();
	public void updateNews(News news);
	public void deleteNews(News news);
	
	public News getByUrlAndTitle(@Param(value = "title") String title
			, @Param(value = "url") String url);
	
 }
