package com.personalizednewsrecommendation.manager.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.*;

import junit.framework.*;

/**
 * 
 * @ClassName CrawlTask
 * @Description TODO
 * @author zhanghui
 * @date May 12, 2018 10:10:23 PM
 * 
 * 静态注入必须写上注解该类为@Component @Service 
 */
@Component
public class CrawlTask {
	private static Logger log = LoggerFactory.getLogger(CrawlTask.class);
	private static String[] urls = {
			NewsWebURL.WANGYI_HOT
			, NewsWebURL.WANGYI_DOMESTIC
			, NewsWebURL.WANGYI_WORLD
			, NewsWebURL.WANGYI_SOCIETY
			, NewsWebURL.WANGYI_WAR
			, NewsWebURL.WANGYI_AIR
			, NewsWebURL.WANGYI_UAV};
	private static int count =1;
	private static CrawlManager crawlManager ;
	
	@Resource(name = "crawlManager")
	public void setCrawlManager(CrawlManager crawlManager){
		CrawlTask.crawlManager = crawlManager;
	}
	
	public static void  runSpider() {
		//Assert.assertNotNull(crawlManager);
		log.info("crawlmanage is null :{}",crawlManager == null);
		crawlManager.callbackDataCrawl(count, urls);
	}
}
