package com.personalizednewsrecommendation.manager.dao.mongodb;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.personalizednewsrecommendation.manager.pojo.*;

@Repository("topNewsDAO")
public class TopNewsDAO extends MongodbDAOImpl<TopNews>{

	@Override
	public Class<TopNews> getEntityClass() {
		// TODO Auto-generated method stub
		return TopNews.class;
	}

	@Override
	public String getCollectionName() {
		// TODO Auto-generated method stub
		return "todaynews";
	}
	
}
