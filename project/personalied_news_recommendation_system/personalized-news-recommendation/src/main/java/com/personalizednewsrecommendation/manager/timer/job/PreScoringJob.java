package com.personalizednewsrecommendation.manager.timer.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PreScoringJob extends QuartzJobBean {

	private static Logger log=LoggerFactory.getLogger(PreScoringJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("日志处理更新评分 user-item");
	}

}
