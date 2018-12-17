

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.servicedefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.stereotype.Component;
import com.newrelic.automotive.exception.*;
import com.newrelic.automotive.servicedefinition.IOrderEntryUtilitiesInterface; 
/**
Set of Order Entry Utilities to process orders in a better fashion
*/
@Component
public abstract class AbstactOrderEntryUtilities implements IOrderEntryUtilitiesInterface{

	
				
				/**
				Documentation :: Edit order number item
				*/
				
				public Boolean editOrderNumber(int OrderNumber) throws Exception {
					
					return null; 
				}
			
}