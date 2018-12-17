
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao;


import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.newrelic.momentum.config.WebConfig;

import com.newrelic.automotive.dao.mom.extension.impl.CustomerDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class })
@WebAppConfiguration
public class CustomerDAOTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	private SessionFactory sessionFactory;

	@InjectMocks
	private CustomerDAOImpl customerDAOImpl;
	

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
		
	}	
	
	@Test
	public void testGetAllCustomersWhenException() throws Exception {
		
	}

	@Test
	public void testAddCustomer() throws Exception {

	}
	
	@Test
	public void testAddCustomerWhenException() throws Exception {

	}

	@Test
	public void testUpdateCustomer() throws Exception {

	}
	
	@Test
	public void testUpdateCustomerWhenException() throws Exception {

	}
	
	@Test
	public void testDeleteCustomer() throws Exception {

	}
	
	@Test
	public void testDeleteCustomerWhenException() throws Exception {

	}

}