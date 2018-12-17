
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.handler;
	
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newrelic.automotive.response.InvoiceResponse;;
		
/**
 * Represent Java Object to XML
 * 
 */
@XmlRootElement(name = "invoices")
public class InvoiceList {

    private List<InvoiceResponse> data;

	@XmlElement(name = "invoice")
	public List<InvoiceResponse> getData() {
		return data;
	}

	public void setData(List<InvoiceResponse> data) {
		this.data = data;
	}

}
		