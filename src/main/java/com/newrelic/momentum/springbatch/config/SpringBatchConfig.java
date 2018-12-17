package com.newrelic.momentum.springbatch.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.newrelic.momentum.springbatch.JobListener;
import com.newrelic.momentum.springbatch.ResultItemProcessor;

/**
 * Configuration file to configure all the spring-batch related beans and job details.
 * getting rid of all the xml configuration and moving to java annotation configuration
 */

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:springbatch.properties")
public class SpringBatchConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBatchConfig.class);
	
	@Autowired
	Environment env;

	@Autowired
	DataSource dataSource;

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public JobRepository getJobRepository() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
		factory.setTransactionManager(getTransactionManager());
		factory.afterPropertiesSet();
		return (JobRepository) factory.getObject();
	}

	@Bean
	@Primary
	public PlatformTransactionManager getTransactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
		JobRegistryBeanPostProcessor pp = new JobRegistryBeanPostProcessor();
		pp.setJobRegistry(jobRegistry());
		return pp;
	}

	@Bean
	public MapJobRegistry jobRegistry() {
		return new MapJobRegistry();
	}

	@Bean
	public JdbcCursorItemReader databaseItemReader() {
		JdbcCursorItemReader databaseItemReader = new JdbcCursorItemReader();
		databaseItemReader.setDataSource(dataSource);
		databaseItemReader.setSql(env.getProperty("selectStmt"));
		databaseItemReader.setRowMapper(columnMapRowMapper());
		return databaseItemReader;
	}
	  
	@Bean
	public ColumnMapRowMapper columnMapRowMapper() {
		return new ColumnMapRowMapper();
	}

	@Bean
	public JdbcBatchItemWriter writer() {
		logger.info("Creating the bean writer");
		JdbcBatchItemWriter databaseItemWriter = new JdbcBatchItemWriter();
		databaseItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		databaseItemWriter.setSql(env.getProperty("insertStmt"));
		databaseItemWriter.setDataSource(dataSource);
		return databaseItemWriter;
	}

	@Bean
	public JobListener jobListener() {
		return new JobListener();
	}

	@Bean
	public ResultItemProcessor itemProcessor() {
		return new ResultItemProcessor();
	}

	@Bean
	public Job userResultJob() {
		return jobs.get("userResultJob").listener(jobListener()).start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Object, Object> chunk(10)
				.reader(databaseItemReader()).processor(itemProcessor())
				.writer(writer()).listener(jobListener())
				.build();
	}
}
