package com.personalizednewsrecommendation.manager.timer.job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.personalizednewsrecommendation.manager.spider.CallbackDataPipline;
public class RecommenderJob extends QuartzJobBean{

	private static Logger log=LoggerFactory.getLogger(RecommenderJob.class);
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println(context.getClass().toGenericString());
		log.info("定时任务启动，计算user-item推荐结果缓存或者websoket推荐");
		/**
		 * 
		 */
		
	}

}
