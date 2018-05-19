package com.personalizednewsrecommendation.manager.spider;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service("crawlManager")
public class CrawlManagerImpl implements CrawlManager{

	
	@Resource(name="customPipeline")
	private CustomPipeline customPipeline;
	
	@Resource(name="callbackDataPipline")
	private CallbackDataPipline callbackDataPipline;
	
	@Override
	public void newsCrawl(int count, String... urls) {
		// TODO Auto-generated method stub
		Spider.create(new NewsSpider()).addPipeline(customPipeline)
		.addUrl(urls)
		.thread(count)
		.run();
	}

	@Override
	public void callbackDataCrawl(int count, String... urls) {
		// TODO Auto-generated method stub
		Spider.create(new CallBackDataSpider()).addPipeline(callbackDataPipline)
		.addUrl(urls)
		.thread(count)
		.run();
	}

}
