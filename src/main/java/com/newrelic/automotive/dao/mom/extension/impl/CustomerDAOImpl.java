
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao.mom.extension.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.dao.AbstractCustomerDAOImpl;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.CustomerResponse;


/**
 * CustomerImpl DAO Class
 * @author Ciber Momentum
 */

@Component
public class CustomerDAOImpl extends AbstractCustomerDAOImpl{

	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
       
}