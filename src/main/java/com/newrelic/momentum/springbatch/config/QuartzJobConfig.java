package com.newrelic.momentum.springbatch.config;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.newrelic.momentum.springbatch.JobLauncherDetails;

/**
 * Configuration file to configure all the Quartz related beans and trigger details.
 * getting rid of all the xml configuration and moving to java annotation configuration
 */

@Configuration
public class QuartzJobConfig {

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobConfig.class);

	@Autowired
	MapJobRegistry jobRegistry;

	@Autowired
	JobLauncher getJobLauncher;

	@Bean
	public SchedulerFactoryBean scheduler() {
		logger.info("Inside scheduler bean creation");
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		Trigger[] triggers = { cronTrigger().getObject() };
		schedulerFactoryBean.setTriggers(triggers);
		return schedulerFactoryBean;
	}

	@Bean()
	public CronTriggerFactoryBean cronTrigger() {
		CronTriggerFactoryBean cronTriggerBean = new CronTriggerFactoryBean();
		cronTriggerBean.setJobDetail(jobDetail().getObject());
		cronTriggerBean.setCronExpression("0 0/1 * 1/1 * ? *");
		return cronTriggerBean;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetailBean = new JobDetailFactoryBean();
		jobDetailBean.setJobClass(jobLauncherDetails().getClass());
		jobDetailBean.setGroup("quartz-batch");
		jobDetailBean.setJobDataAsMap(jobDataMap());
		return jobDetailBean;
	}

	@Bean
	public JobLauncherDetails jobLauncherDetails() {
		return new JobLauncherDetails();
	}

	@Bean
	public Map<String, Object> jobDataMap() {
		Map<String, Object> jobMap = new HashMap<>();
		jobMap.put("jobName", "userResultJob");
		jobMap.put("jobLocator", jobRegistry);
		jobMap.put("jobLauncher", getJobLauncher);
		return jobMap;
	}

}
