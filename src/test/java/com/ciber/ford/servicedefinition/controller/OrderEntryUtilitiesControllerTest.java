

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.servicedefinition.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.minidev.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.newrelic.momentum.config.WebConfig;

import com.newrelic.automotive.exception.*;

import com.newrelic.automotive.servicedefinition.controller.OrderEntryUtilitiesController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class OrderEntryUtilitiesControllerTest {

		
	@InjectMocks
	public OrderEntryUtilitiesController orderentryutilitiesController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderentryutilitiesController).build();		
	}
		
				@Test
				public void test_editOrderNumber() throws Exception {
						/* Sample code to test the service... Based on requirement and intended test case service methods name and parameters gets changed.
						Response resp = new Response();
				        resp.setResponseCode("YYYY");
				        resp.setResponseData(null);
				        resp.setResponseMessage("Operation Successful");
				        
				        when(customerserviceController.getAllCustomers()).thenReturn(resp);        
				        
				        
				        
				        this.mockMvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				        .andDo(print())
				        .andExpect(status().isOk())
				        .andExpect(jsonPath("responseCode").value("YYYY"))
				        .andExpect(jsonPath("responseMessage").value("Operation Successful"))
				        .andExpect(jsonPath("responseData").value(JSONObject.escape(null)));  */
					
				}
				
}