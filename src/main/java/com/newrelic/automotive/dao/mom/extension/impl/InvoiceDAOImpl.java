
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao.mom.extension.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.dao.AbstractInvoiceDAOImpl;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.InvoiceResponse;


/**
 * InvoiceImpl DAO Class
 * @author Ciber Momentum
 */

@Component
public class InvoiceDAOImpl extends AbstractInvoiceDAOImpl{

	private static final Logger logger = LoggerFactory.getLogger(InvoiceDAOImpl.class);
       
}