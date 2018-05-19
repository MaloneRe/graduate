package com.personalizednewsrecommendation.manager.controller.statistics;

import java.util.concurrent.Callable;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;

import com.personalizednewsrecommendation.manager.service.*;

import org.apache.mahout.cf.taste.recommender.*;

import java.util.*;

import com.personalizednewsrecommendation.manager.algorithm.mahout.*;
import com.personalizednewsrecommendation.manager.pojo.*;

public class ItemCFCallable implements Callable<Object>{
	
	private NewsService newsService;
	private UserService userService;
	
	private DataModel dataModel;
	private int at;
	public  ItemCFCallable(DataModel dataModel
			, int at
			, UserService userService
			, NewsService newsService) {
		this.dataModel = dataModel;
		this.at = at;
		this.userService = userService;
		this.newsService = newsService;
	}
	
	@Override
	public Object call() throws Exception {
		Map<String, Object> map = new HashMap<>();
		Recommender recommender =
				RecommenderEvaluator
				.itemCF(dataModel, at, map)
				.buildRecommender(dataModel);
		List<Object> sList = new java.util.ArrayList<>();
		LongPrimitiveIterator iter = dataModel.getUserIDs();
		
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = recommender.recommend(uid, at);
            sList.add(show(uid, list));
        }
		Map<String, Object> result = new HashMap<>();
		result.put("evaluate", map);
		result.put("result", sList);
		return result;
	}
	
	public Map<String, Object> show(Long uid, List<RecommendedItem> list){
		Map<String, Object> map = new java.util.HashMap<>();
		map.put("user", userService.getUser(uid));
		List<News> news = new java.util.ArrayList<>();
		for (RecommendedItem recommendedItem : list) {
			news.add(newsService.findByID(recommendedItem.getItemID()));
		}
		map.put("news", news);
		return map;
	}

}
