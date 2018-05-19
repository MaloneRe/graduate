package com.personalizednewsrecommendation.manager.spider;

public interface CrawlManager {
	
	/**
	 * 
	 * @Method: newsCrawl 
	 * @Description: 启动爬虫爬取新闻
	 * @param url
	 * @throws
	 */
	public void newsCrawl(int count, String... urls);
	
	public void callbackDataCrawl(int count, String... urls);
}
