
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

import com.newrelic.automotive.handler.InvoiceList;
import com.newrelic.momentum.annotation.Auditable;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.response.Response;
import com.newrelic.automotive.service.InvoiceService;
import com.newrelic.automotive.constant.Constants;
		
/**
 * REST Service Provider * 
 * Only GET and PUT will return values, POST and DELETE will not.
 */
@Controller
public class InvoiceController {

    private static final Logger logger = LoggerFactory
			.getLogger(InvoiceController.class);
        	
	@Autowired
	public InvoiceService invoiceService;

    
	@Auditable("GetAllInvoices")
	@RequestMapping(value = "/invoices", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response getAllInvoices() {
		Response response = new Response();
		InvoiceList result = new InvoiceList();
		try {
			result.setData(invoiceService.getAllInvoices());
			response.setResponseData(result);
			response.setResponseCode(Constants.ALL_INVOICES_FETCH_SUCCESS_CODE);
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred in getAllInvoices", e );
			response.setResponseCode(Constants.ALL_INVOICES_FETCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}    
	
	@Auditable("AddInvoice")
	@RequestMapping(value = "/addInvoice", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response addInvoice(@RequestBody Invoice invoice) {
		Response response = new Response();
		try {			
			boolean status = invoiceService.addInvoice(invoice);
			if (status) {
				response.setResponseCode(Constants.INVOICE_ADD_SUCCESS_CODE);
				response.setResponseMessage("Invoice Added Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.INVOICE_ADD_FAIL_CODE);
				response.setResponseMessage("There is some issue during add of Invoice");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during addInvoice", e );
			response.setResponseCode(Constants.INVOICE_ADD_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}		
	}	

	@Auditable("UpdateInvoice")
	@RequestMapping(value = "/updateInvoice", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateInvoice(@RequestBody Invoice invoice) {
		Response response = new Response();
		try {
			boolean status = invoiceService.updateInvoice(invoice);
			if (status) {
				response.setResponseData(invoice);
				response.setResponseCode(Constants.INVOICE_UPDATE_SUCCESS_CODE);
				response.setResponseMessage("Invoice Updated Successfully.");
				return response;
			} else {
				response.setResponseCode(Constants.INVOICE_UPDATE_FAIL_CODE);
				response.setResponseMessage("There is some issue during update Invoice");
				return response;
			}
		} catch (Exception e) {
			logger.error("Exception occurred during updateInvoice", e );
			response.setResponseCode(Constants.INVOICE_UPDATE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	}
	
	@Auditable("DeleteInvoice")
	@RequestMapping(value = "/deleteInvoice", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response deleteInvoice(@RequestBody Invoice invoice) {
		Response response = new Response();
		try {
			boolean status = invoiceService.deleteInvoice(invoice);
			if(status){
				response.setResponseData(status);
				response.setResponseCode(Constants.INVOICE_DELETE_SUCCESS_CODE);
				response.setResponseMessage("Invoice Deleted Successfully.");
				return response;
			}else{
				response.setResponseCode(Constants.INVOICE_DELETE_FAIL_CODE);
				response.setResponseMessage("There is some issue during delete Invoice");
				return response;
			}			
		} catch (Exception e) {
			logger.error("Exception occurred during deleteInvoice", e );
			response.setResponseCode(Constants.INVOICE_DELETE_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;			
		}
	}	

	@Auditable("SearchInvoice")
	@RequestMapping(value = "/searchInvoice", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response searchInvoice(@RequestBody Invoice invoice) {
		Response response = new Response();
		InvoiceList result = new InvoiceList();
		try {
			result.setData(invoiceService.getInvoice(invoice));
			response.setResponseData(result);
			response.setResponseCode(Constants.INVOICE_SEARCH_SUCCESS_CODE);
			response.setResponseMessage("Invoice Search Request was Successful...");
			return response;
		} catch (Exception e) {
			logger.error("Exception occurred during searchInvoice", e );
			response.setResponseCode(Constants.INVOICE_SEARCH_FAIL_CODE);
			response.setResponseMessage("There is some issue, please contact Administrator");
			return response;
		}
	} 
}
		