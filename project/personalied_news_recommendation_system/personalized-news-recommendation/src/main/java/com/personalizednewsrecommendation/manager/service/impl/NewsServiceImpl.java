package com.personalizednewsrecommendation.manager.service.impl;

import com.personalizednewsrecommendation.manager.dao.*;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.*;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.*;
@Service("newsService")
public  class NewsServiceImpl implements NewsService {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private NewsDAO newsDAO;
	
	@Override
	public String getDetails(String url) {
		Document doc = null;
		Elements news = null;
		try {
			doc = Jsoup.connect(url).get();
			/**
			 * 新闻主体内容
			 */
			news = doc.select("div#epContentLeft.post_content_main");
			/**
			 *新闻的推荐 
			 */
			//news.select("div#post_recommend.post_recommend");
			news.select("div#post_recommend.post_recommend").remove();
			/**
			 * 发帖栏
			 */
		    news.select("div#post_comment_area.post_comment").remove(); 
		    /**
		     * 分享栏
		     */
		    news.select("div.post_topshare_wrap").remove();
			news.select("div.post_btmshare").remove();

		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return news.toString();
	}

	@Override
	public List<News> findByCategory(String name) {
		Category category = categoryDAO.getByName(name);
		Long cate = null;
		if (category != null) {
			cate = category.getId(); 
		}else{
			cate = (long)9;
		}
		return newsDAO.getNewsesById(cate);
	}

	@Override
	public Long createNews(News news) {
		News news2 = newsDAO.getByUrlAndTitle(news.getTitle(), news.getUrl());
		Long id = null;
		if(news2 == null){
			newsDAO.insertNews(news);
			id  = news.getId();
		}else{
			id = news2.getId();
			Long cate = news2.getCategoryId();
			String imgurl = news2.getImageUrl();
			news.setId(id);
			if ( cate != null && imgurl != null) {
				news.setImageUrl(imgurl);
				news.setCategoryId(cate);
			}else {
				newsDAO.updateNews(news);
			}
		}
		
		return id;
	}

	@Override
	public String getDetails(Long nid) {
		News news = newsDAO.getNewsById(nid);
		String content =  this.getDetails(news.getUrl());
		return content;
	}

	@Override
	public News findByID(Long id) {
		News news = null;
		if (id != null) {
			news = newsDAO.getNewsById(id);
		}
		return news;
	}
	
}
