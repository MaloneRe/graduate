package com.personalizednewsrecommendation.manager.service;

import java.util.*;
import java.*;
import com.personalizednewsrecommendation.manager.pojo.*;

public interface NewsService {
	public List<News> findByCategory(String name);
	public String getDetails(String url);
	public String getDetails(Long nid);

	public Long createNews(News news);
	
	public News findByID(Long id);
}
