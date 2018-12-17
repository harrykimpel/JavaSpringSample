

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.newrelic.automotive.response.Response;
import com.newrelic.automotive.servicedefinition.IChkdgrtnInterface;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.exception.*;
/**
Check digit validation
*/
@Controller
public class ChkdgrtnController {

	private static final Logger logger = LoggerFactory.getLogger(ChkdgrtnController.class);
	
	@Autowired
	public IChkdgrtnInterface service;
	
				
				/**
				Documentation :: Check Digit Validation
				*/
				
				@POST
				@RequestMapping(value = "/chkdgrtn", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
				@Consumes({"application/JSON","application/ XML"})  
				@Produces({"application/JSON","application/ XML"})
				public @ResponseBody Response chkdgrtn(@RequestBody Customer customer) {
					Response response = new Response();
					//Set to response.setResponseData(), if any data to be returned..
					
					try {
						Boolean ret = service.chkdgrtn(customer.getCustomerNumber());
						response.setResponseCode("101");
						  response.setResponseMessage("The check digit of the customer number is valid!");
						  
						  if (!ret)
						  {
							  response.setResponseCode("102");
							  response.setResponseMessage("The check digit of the customer number is invalid!");
						  }
					} 
					
							catch(Exception exception) {
								response.setResponseCode("9999");
								response.setResponseMessage("InvalidCheckDigit");
								return response;
							}
					
					return response;		
				}
				
				
		
}