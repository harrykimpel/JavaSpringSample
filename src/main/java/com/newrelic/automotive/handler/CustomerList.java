
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.handler;
	
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newrelic.automotive.response.CustomerResponse;;
		
/**
 * Represent Java Object to XML
 * 
 */
@XmlRootElement(name = "customers")
public class CustomerList {

    private List<CustomerResponse> data;

	@XmlElement(name = "customer")
	public List<CustomerResponse> getData() {
		return data;
	}

	public void setData(List<CustomerResponse> data) {
		this.data = data;
	}

}
		