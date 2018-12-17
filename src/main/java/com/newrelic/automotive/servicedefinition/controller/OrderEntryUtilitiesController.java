

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.servicedefinition.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.newrelic.automotive.response.Response;
import com.newrelic.automotive.servicedefinition.IOrderEntryUtilitiesInterface; 
import com.newrelic.automotive.exception.*;
/**
Set of Order Entry Utilities to process orders in a better fashion
*/
@Controller
@RequestMapping("/OrderEntryUtilities")
public class OrderEntryUtilitiesController {

	private static final Logger logger = LoggerFactory.getLogger(OrderEntryUtilitiesController.class);
	
	@Autowired
	public IOrderEntryUtilitiesInterface service;
	
				
				/**
				Documentation :: Edit order number item
				*/
				
				@POST
				@RequestMapping(value = "/editOrderNumber")
				
				@Consumes({"application/JSON","application/ XML"})  
				@Produces({"application/JSON","application/ XML"})
				@ResponseBody
				public Response editOrderNumber(int OrderNumber) {
					Response response = new Response();
					//Set to response.setResponseData(), if any data to be returned..
					
					try {
						  service.editOrderNumber(OrderNumber);
					} 
					
							catch(Exception exception) {
								response.setResponseCode("9999");
								response.setResponseMessage("InvalidOrder");
								return response;
							}
					
					return response;		
				}
				
				
		
}