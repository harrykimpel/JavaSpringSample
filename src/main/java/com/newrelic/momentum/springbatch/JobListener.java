package com.newrelic.momentum.springbatch;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Program to Track all the stages of the Job Run
 *
 */
public class JobListener implements JobExecutionListener{
	
	private static final Logger logger = LoggerFactory.getLogger(JobListener.class);

	private DateTime startTime, stopTime;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = new DateTime();
		logger.info(" Job starts at :"+startTime);
	}
	

	@Override
	public void afterJob(JobExecution jobExecution) {
		stopTime = new DateTime();
		logger.info("  Job stops at :"+stopTime);
		if(startTime != null)
		logger.info("Total time take in millis :"+getTimeInMillis(startTime , stopTime));
		
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			logger.info("  job completed successfully");
		}else if(jobExecution.getStatus() == BatchStatus.FAILED){
			logger.error("  job failed with following exceptions ");
			List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
			for(Throwable th : exceptionList){
				logger.error("exception :" +th.getLocalizedMessage());
			}
		}
	}
	
	private long getTimeInMillis(DateTime start, DateTime stop){
		return stop.getMillis() - start.getMillis();
	}
	
}
