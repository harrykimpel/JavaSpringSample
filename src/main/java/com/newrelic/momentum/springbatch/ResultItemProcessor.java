package com.newrelic.momentum.springbatch;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class ResultItemProcessor implements ItemProcessor<Object, Object> {

	private static final Logger logger = LoggerFactory.getLogger(ResultItemProcessor.class);
	@Override
	public Object process(Object obj) throws Exception {

		logger.info("Inside ResultItemProcessor.process() method-");

		java.util.Map map = (Map) obj;

		for (Object key : map.keySet()) {
			logger.info("Processing the record for Key : " + key.toString() + " Value : "
					+ map.get(key));
		}

		return obj;
	}

}
