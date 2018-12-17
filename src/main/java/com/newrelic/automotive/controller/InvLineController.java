
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

import com.newrelic.automotive.handler.InvLineList;
import com.newrelic.momentum.annotation.Auditable;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.response.Response;
import com.newrelic.automotive.service.InvLineService;
import com.newrelic.automotive.constant.Constants;
		
/**
 * REST Service Provider * 
 * Only GET and PUT will return values, POST and DELETE will not.
 */
@Controller
public class InvLineController {

    private static final Logger logger = LoggerFactory
			.getLogger(InvLineController.class);
        	
	@Autowired
	public InvLineService invLineService;

    
	@Auditable("GetAllInvLines")
	@RequestMapping(value = "/invLines", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response getAllInvLines() {
		Response response = new Response();
		InvLineList result = new InvLineList();
		try {
			result.setData(invLineService.getAllInvLines());
			response.setResponseData(result);
			response.setResponseCode(Constants.ALL_INVLINES_FETCH_SUCCESS_CODE);
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred in getAllInvLines", e );
			response.setResponseCode(Constants.ALL_INVLINES_FETCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}    
	
	@Auditable("AddInvLine")
	@RequestMapping(value = "/addInvLine", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response addInvLine(@RequestBody InvLine invLine) {
		Response response = new Response();
		try {			
			boolean status = invLineService.addInvLine(invLine);
			if (status) {
				response.setResponseCode(Constants.INVLINE_ADD_SUCCESS_CODE);
				response.setResponseMessage("InvLine Added Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.INVLINE_ADD_FAIL_CODE);
				response.setResponseMessage("There is some issue during add of InvLine");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during addInvLine", e );
			response.setResponseCode(Constants.INVLINE_ADD_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}		
	}	

	@Auditable("UpdateInvLine")
	@RequestMapping(value = "/updateInvLine", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateInvLine(@RequestBody InvLine invLine) {
		Response response = new Response();
		try {
			boolean status = invLineService.updateInvLine(invLine);
			if (status) {
				response.setResponseData(invLine);
				response.setResponseCode(Constants.INVLINE_UPDATE_SUCCESS_CODE);
				response.setResponseMessage("InvLine Updated Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.INVLINE_UPDATE_FAIL_CODE);
				response.setResponseMessage("There is some issue during update InvLine");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during updateInvLine", e );
			response.setResponseCode(Constants.INVLINE_UPDATE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}
	
	@Auditable("DeleteInvLine")
	@RequestMapping(value = "/deleteInvLine", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response deleteInvLine(@RequestBody InvLine invLine) {
		Response response = new Response();
		try {
			boolean status = invLineService.deleteInvLine(invLine);
			if(status){
				response.setResponseData(status);
				response.setResponseCode(Constants.INVLINE_DELETE_SUCCESS_CODE);
				response.setResponseMessage("InvLine Deleted Successfully.");
				return response;
			}else{
				response.setResponseCode(Constants.INVLINE_DELETE_FAIL_CODE);
				response.setResponseMessage("There is some issue during delete InvLine");
				return response;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred during deleteInvLine", e );
			response.setResponseCode(Constants.INVLINE_DELETE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;			
		}
	}	

	@Auditable("SearchInvLine")
	@RequestMapping(value = "/searchInvLine", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response searchInvLine(@RequestBody InvLine invLine) {
		Response response = new Response();
		InvLineList result = new InvLineList();
		try {
			result.setData(invLineService.getInvLine(invLine));
			response.setResponseData(result);
			response.setResponseCode(Constants.INVLINE_SEARCH_SUCCESS_CODE);
			response.setResponseMessage("InvLine Search Request was Successful...");
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred during searchInvLine", e );
			response.setResponseCode(Constants.INVLINE_SEARCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	} 
}
		