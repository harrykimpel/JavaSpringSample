
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.controller;
	
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newrelic.automotive.exception.UnknownResourceException;

/**
 * Default controller that exists to return a proper REST response for unmapped
 * requests.
 */ 
@Controller
public class DefaultController {
	private static final Logger logger = LoggerFactory
			.getLogger(DefaultController.class);
	@RequestMapping("/**")
	public void unmappedRequest(HttpServletRequest request) {
		logger.info("Inside DefaultController.unmappedRequest()");
		String uri = request.getRequestURI();
		throw new UnknownResourceException("There is no resource for path "
				+ uri);
	}
}
		