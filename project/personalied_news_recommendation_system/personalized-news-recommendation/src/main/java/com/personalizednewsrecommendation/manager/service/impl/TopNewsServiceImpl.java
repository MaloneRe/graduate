package com.personalizednewsrecommendation.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.personalizednewsrecommendation.manager.dao.mongodb.TopNewsDAO;
import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.TopNewsService;

@Service("topNewsService")
public class TopNewsServiceImpl implements TopNewsService{
	@Resource(name = "topNewsDAO")
	private TopNewsDAO topNewsDAO;

	@Override
	public void saveTodayNews(TopNews topNews) {
		
		TopNews nTopNews = topNewsDAO.findOne(new Query());
		if (nTopNews != null) {
			String id = nTopNews.getId();
			topNews.setId(id);
			Criteria criteria = Criteria.where("id")
					.is(id);
			Query query = new Query(criteria);
			Update update = new Update();
			update.set("newses", topNews.getNewses());
			update.set("content", topNews.getContent());
			topNewsDAO.update(query, update);
		}else {
			topNewsDAO.save(topNews);
		}
	}

	@Override
	public List<News> getTodayNews() {
		TopNews nTopNews = topNewsDAO.findOne(new Query());
		if (nTopNews != null) {
			return nTopNews.getNewses();
		}
		return null;
	}
	
	
}
