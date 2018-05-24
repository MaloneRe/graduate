package com.personalizednewsrecommendation.manager.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.*;

import com.alibaba.fastjson.*;

import com.personalizednewsrecommendation.manager.pojo.*;
import com.personalizednewsrecommendation.manager.service.*;
import com.personalizednewsrecommendation.manager.spider.CallbackDataPipline;
import com.personalizednewsrecommendation.manager.spider.NewsWebURL;

import org.springframework.beans.factory.annotation.*;



@Component("crawlQueueReceiver")
public class CrawlQueueReceiver implements MessageListener {
	private static Logger log=LoggerFactory.getLogger(CrawlQueueReceiver.class);
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage= (ObjectMessage) message;
			Object object = null;
			try {
				object = objectMessage.getObject();
				if (object instanceof Map<?, ?>) {
					Map<String, Object> map = (Map<String, Object>) object;
					if (map.get("newses") instanceof List<?>) {
						log.info("jsonObject newses is JSONArray son");
						String url = (String)  map.get("url");
						List<News> newses = (List<News>)map.get("newses");
						log.info("================crawl receiver start ============");
						log.info("url is :{}\n news:{}"
								, url
								, JSONArray.toJSON(newses));
						/**
						 * 插入数据库
						 */
						this.insertNewsDB(url, newses);
					}
				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Autowired
	private NewsService newsService;
	public void insertNewsDB(String url, List<News> newses){
		for (News news : newses) {
			if (url.equals(NewsWebURL.WANGYI_DOMESTIC)) {
				news.setCategoryId((long)1);
			}else if(url.equals(NewsWebURL.WANGYI_WORLD)){
				news.setCategoryId((long)2);
			}else if (url.equals(NewsWebURL.WANGYI_SOCIETY)) {
				news.setCategoryId((long)3);
			}else if (url.equals(NewsWebURL.WANGYI_WAR)) {
				news.setCategoryId((long)4);
			}else if (url.equals(NewsWebURL.WANGYI_AIR)) {
				news.setCategoryId((long)5);
			}else if (url.equals(NewsWebURL.WANGYI_UAV)) {
				news.setCategoryId((long)6);
			}else {
				news.setCategoryId((long)9);
			}
			newsService.createNews(news);
		}
	}

}
