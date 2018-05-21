package com.personalizednewsrecommendation.manager.timer.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.personalizednewsrecommendation.manager.spider.CallbackDataPipline;

import com.personalizednewsrecommendation.manager.spider.*;

import junit.framework.Assert.*;

public class NewsUpdateJob extends QuartzJobBean {

	private static Logger log=LoggerFactory.getLogger(NewsUpdateJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(context.getClass().toGenericString());
		log.info("定时任务启动，爬取新闻存到数据库");
		CrawlTask.runSpider();
	}

}
