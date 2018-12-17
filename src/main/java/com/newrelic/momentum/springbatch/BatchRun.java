package com.newrelic.momentum.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.newrelic.momentum.config.WebConfig;
import com.newrelic.momentum.springbatch.config.SpringBatchConfig;

/**
 * Program to run the job manually by passing the SQL Statements as Job Input
 * Parameters.
 */
public class BatchRun {

	private static final Logger logger = LoggerFactory.getLogger(BatchRun.class);

	public static void main(String[] args) {

		logger.info("Before Initilizing the Spring Batch Context");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(WebConfig.class);
		context.register(SpringBatchConfig.class);
		context.refresh();

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("userResultJob");

		try {
			logger.info("Before Executing the Job");
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			logger.info("Job Exit Status : " + execution.getStatus());

		} catch (JobExecutionException e) {
			logger.error("Job userResultJob failed::", e);
		}
	}

}
