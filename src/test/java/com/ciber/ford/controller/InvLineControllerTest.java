
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.controller;
	
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

import com.newrelic.automotive.constant.Constants;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.service.InvLineService;
import com.newrelic.momentum.config.WebConfig;
import com.newrelic.automotive.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class InvLineControllerTest {
	
	@Mock
	public InvLineService invLineService;

	@InjectMocks
	private InvLineController invLineController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(invLineController).build();		
	}
	
	@Test
    public void testAddInvLine_WhenInvLineAddedSuccessfully() throws Exception{ 		
        //Update the below code as per your test entity.
		
        /*Response resp = new Response();
        resp.setResponseCode(Constants.USER_ADD_SUCCESS_CODE);
        resp.setResponseData(null);
        resp.setResponseMessage("User Added Successfully...");
        
        when(userControllerHelper.addUser(any(User.class))).thenReturn(resp);        
        
        User addUserTest = new User();
        addUserTest.setName("Ciber User");    
        addUserTest.setFirstName("Momentum");
        addUserTest.setLastName("CIBER");
        
        this.mockMvc.perform(post("/addUser", addUserTest).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                             .content("{\"name\" : \"" + addUserTest.getName() + "\",\"firstName\" : \"" + addUserTest.getFirstName() + "\", \"lastName\" : \"" + addUserTest.getLastName() +"\" }"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("responseCode").value(Constants.USER_ADD_SUCCESS_CODE))
        .andExpect(jsonPath("responseMessage").value("User Added Successfully..."))
        .andExpect(jsonPath("responseData").value(JSONObject.escape(null)));  */       
    }
	
	@Test
    public void testUpdateInvLine_WhenInvLineUpdatedSuccessfully() throws Exception{ 		
        //Update the below code as per your test entity.
		
		/*User updateUserTest = new User();
		updateUserTest.setName("Ciber User");    
		updateUserTest.setFirstName("Momentum");
		updateUserTest.setLastName("CIBER");
        
        Response response = new Response();
        response.setResponseData(updateUserTest);
		response.setResponseCode(Constants.USER_UPDATE_SUCCESS_CODE);
		response.setResponseMessage("User Updated Successfully...");
        
        when(userControllerHelper.updateUser(any(User.class))).thenReturn(response);        
        
        this.mockMvc.perform(put("/updateUser", updateUserTest).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"" + updateUserTest.getName() + "\",\"firstName\" : \"" + updateUserTest.getFirstName() + "\", \"lastName\" : \"" + updateUserTest.getLastName() +"\" }"))
                 .andDo(print())
                 .andExpect(jsonPath("responseCode").value(Constants.USER_UPDATE_SUCCESS_CODE))
                 .andExpect(jsonPath("responseMessage").value("User Updated Successfully..."));  */
        		 //.andExpect(jsonPath("responseData").value(updateUserTest));          
    }
	
	@Test
    public void testDeleteInvLine_WhenInvLineDeletedSuccessfully() throws Exception{ 		
        //Update the below code as per your test entity.
		
		/*User deleteUserTest = new User();
		deleteUserTest.setId(12);
		deleteUserTest.setName("Ciber User");    
		deleteUserTest.setFirstName("Momentum");
		deleteUserTest.setLastName("CIBER");
		
		boolean status = true;
        
        Response response = new Response();
        response.setResponseData(status);
		response.setResponseCode(Constants.USER_DELETE_SUCCESS_CODE);	
		response.setResponseMessage("User Deleted Successfully...");
        
        when(userControllerHelper.deleteUser(any(User.class))).thenReturn(response);        
                
        this.mockMvc.perform(delete("/deleteUser", deleteUserTest).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        		.content("{\"name\" : \"" + deleteUserTest.getName() + "\",\"firstName\" : \"" + deleteUserTest.getFirstName() + "\", \"lastName\" : \"" + deleteUserTest.getLastName() +"\" }"))
                .andDo(print())
                .andExpect(jsonPath("responseCode").value(Constants.USER_DELETE_SUCCESS_CODE))
                .andExpect(jsonPath("responseMessage").value("User Deleted Successfully..."));*/
                //.andExpect(jsonPath("responseData").value(JSONObject.escape(null)));      
    }
	
	@Test
    public void testSearchInvLineByName_WhenInvLineIsFound() throws Exception{ 		
        //Update the below code as per your test entity.
		
		/*User testUserFound = new User();
		testUserFound.setName("Ciber User");    
		testUserFound.setFirstName("Momentum");
		testUserFound.setLastName("CIBER");
        
        Response response = new Response();
        response.setResponseData(testUserFound);
		response.setResponseCode(Constants.USER_SEARCH_SUCCESS_CODE);
		response.setResponseMessage("Search Request was Successfull...");
        
        when(userControllerHelper.searchUser(any(User.class))).thenReturn(response);        
        
        this.mockMvc.perform(post("/searchUser", testUserFound).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"" + testUserFound.getName() +"\" }"))
                 .andDo(print())
                 .andExpect(jsonPath("responseCode").value(Constants.USER_SEARCH_SUCCESS_CODE))
                 .andExpect(jsonPath("responseMessage").value("Search Request was Successfull...")) ; */
        		// .andExpect(jsonPath("responseData").value(testUserFound));          
    }
	
}	

		