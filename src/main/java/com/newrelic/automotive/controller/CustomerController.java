
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.controller;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.newrelic.automotive.handler.CustomerList;
import com.newrelic.momentum.annotation.Auditable;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.response.Response;
import com.newrelic.automotive.service.CustomerService;
import com.newrelic.automotive.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import com.newrelic.api.agent.NewRelic;

/**
 * REST Service Provider * 
 * Only GET and PUT will return values, POST and DELETE will not.
 */
@Controller
public class CustomerController {

    private static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);
        	
	@Autowired
	public CustomerService customerService;

	@Autowired 
	private HttpServletRequest request;

    
	@Auditable("GetAllCustomers")
	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response getAllCustomers() {
		Response response = new Response();
		CustomerList result = new CustomerList();
		
		String requestId = request.getHeader("X-HARRY-ID");
		NewRelic.addCustomParameter ("X-Harry-ID", requestId);
		NewRelic.addCustomParameter ("x-harry-id", requestId);
		NewRelic.addCustomParameter ("X-Harry-key", requestId);
		NewRelic.addCustomParameter ("x-harry-key", requestId);
		NewRelic.addCustomParameter ("Harry-ID", requestId);
		NewRelic.addCustomParameter ("HarryStatic", "static test");

		try {
			result.setData(customerService.getAllCustomers());
			response.setResponseData(result);
			response.setResponseCode(Constants.ALL_CUSTOMERS_FETCH_SUCCESS_CODE);
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred in getAllCustomers", e );
			response.setResponseCode(Constants.ALL_CUSTOMERS_FETCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}    
	
	@Auditable("AddCustomer")
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response addCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		try {			
			boolean status = customerService.addCustomer(customer);
			if (status) {
				response.setResponseCode(Constants.CUSTOMER_ADD_SUCCESS_CODE);
				response.setResponseMessage("Customer Added Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.CUSTOMER_ADD_FAIL_CODE);
				response.setResponseMessage("There is some issue during add of Customer");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during addCustomer", e );
			response.setResponseCode(Constants.CUSTOMER_ADD_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}		
	}	

	@Auditable("UpdateCustomer")
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		try {
			boolean status = customerService.updateCustomer(customer);
			if (status) {
				response.setResponseData(customer);
				response.setResponseCode(Constants.CUSTOMER_UPDATE_SUCCESS_CODE);
				response.setResponseMessage("Customer Updated Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.CUSTOMER_UPDATE_FAIL_CODE);
				response.setResponseMessage("There is some issue during update Customer");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during updateCustomer", e );
			response.setResponseCode(Constants.CUSTOMER_UPDATE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}
	
	@Auditable("DeleteCustomer")
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response deleteCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		try {
			boolean status = customerService.deleteCustomer(customer);
			if(status){
				response.setResponseData(status);
				response.setResponseCode(Constants.CUSTOMER_DELETE_SUCCESS_CODE);
				response.setResponseMessage("Customer Deleted Successfully.");
				return response;
			}else{
				response.setResponseCode(Constants.CUSTOMER_DELETE_FAIL_CODE);
				response.setResponseMessage("There is some issue during delete Customer");
				return response;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred during deleteCustomer", e );
			response.setResponseCode(Constants.CUSTOMER_DELETE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;			
		}
	}	

	@Auditable("SearchCustomer")
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response searchCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		CustomerList result = new CustomerList();
		try {
			result.setData(customerService.getCustomer(customer));
			response.setResponseData(result);
			response.setResponseCode(Constants.CUSTOMER_SEARCH_SUCCESS_CODE);
			response.setResponseMessage("Customer Search Request was Successful...");
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred during searchCustomer", e );
			response.setResponseCode(Constants.CUSTOMER_SEARCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	} 
}
		