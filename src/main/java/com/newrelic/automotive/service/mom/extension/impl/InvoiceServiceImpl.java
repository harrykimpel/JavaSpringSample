
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.service.mom.extension.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.dao.InvoiceDAO;
import com.newrelic.automotive.service.AbstractInvoiceServiceImpl;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.InvoiceResponse;




/**
 * InvoiceServiceImpl Class can be used for overriding the default method implementation.
 * @author Ciber Momentum
 */

@Component
public class InvoiceServiceImpl extends AbstractInvoiceServiceImpl{

	private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

}