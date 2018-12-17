package com.newrelic.momentum.springbatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.newrelic.momentum.config.WebConfig;
import com.newrelic.momentum.springbatch.config.QuartzJobConfig;
import com.newrelic.momentum.springbatch.config.SpringBatchConfig;

/**
 * Program to execute the job based on Quartz Scheduler
 *
 */

public class QuartzMainJobRun {

	@SuppressWarnings("resource")
	public static void main(String areg[]){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebConfig.class);
        context.register(SpringBatchConfig.class);
        context.register(QuartzJobConfig.class);
        context.refresh();
	}

}
