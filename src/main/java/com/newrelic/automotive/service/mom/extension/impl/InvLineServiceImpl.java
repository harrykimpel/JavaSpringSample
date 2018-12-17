
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.service.mom.extension.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.newrelic.automotive.dao.InvLineDAO;
import com.newrelic.automotive.service.AbstractInvLineServiceImpl;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.InvLineResponse;




/**
 * InvLineServiceImpl Class can be used for overriding the default method implementation.
 * @author Ciber Momentum
 */

@Component
public class InvLineServiceImpl extends AbstractInvLineServiceImpl{

	private static final Logger logger = LoggerFactory.getLogger(InvLineServiceImpl.class);

}