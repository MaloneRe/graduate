package com.personalizednewsrecommendation.manager.controller.statistics;


import java.util.*;
import javax.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.*;

import com.personalizednewsrecommendation.manager.algorithm.mahout.RecommendFactory;

import com.personalizednewsrecommendation.manager.service.*;

import org.springframework.stereotype.*;

@Component
public class StatisticsThreadExecutor {
	
	private static UserService userService;
	private static NewsService newsService;
	
	@Resource(name = "userService")
	public UserService getUserService() {
		return userService;
	}
	@Resource(name = "newsService")
	public NewsService getNewsService() {
		return newsService;
	}

	public static Map<String, Object> getResult(int at){
		ExecutorService pool = null;
		try {
			DataModel dataModel = RecommendFactory.buildDataModelByDB();
			// 创建一个线程池
			pool = Executors.newCachedThreadPool();
		
			SVDCallable svdCallable =
					new SVDCallable(dataModel
							, at
							, userService
							, newsService);
			ItemCFCallable itemCFCallable =
					new ItemCFCallable(dataModel
							, at
							, userService
							, newsService);
			UserCFCallable userCFCallable = 
					new UserCFCallable(dataModel
							, at
							, userService
							, newsService);
			Map<String, Object> map = new HashMap<>();
			map.put("SVD", ((Future)pool.submit(svdCallable)).get());
			map.put("itemCF", pool.submit(itemCFCallable).get()) ;
			map.put("userCF", pool.submit(userCFCallable).get());
			return map;
		} catch (TasteException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}finally{
			if (pool != null) {
				// 关闭线程池
				pool.shutdown();
			}
		}
		
		return null;
		
	}
}
