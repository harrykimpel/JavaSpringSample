
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.service.mom.extension.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.dao.CustomerDAO;
import com.newrelic.automotive.service.AbstractCustomerServiceImpl;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.CustomerResponse;




/**
 * CustomerServiceImpl Class can be used for overriding the default method implementation.
 * @author Ciber Momentum
 */

@Component
public class CustomerServiceImpl extends AbstractCustomerServiceImpl{

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

}