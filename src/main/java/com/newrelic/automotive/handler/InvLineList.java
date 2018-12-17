
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/

	
package com.newrelic.automotive.handler;
	
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.newrelic.automotive.response.InvLineResponse;;
		
/**
 * Represent Java Object to XML
 * 
 */
@XmlRootElement(name = "invLines")
public class InvLineList {

    private List<InvLineResponse> data;

	@XmlElement(name = "invLine")
	public List<InvLineResponse> getData() {
		return data;
	}

	public void setData(List<InvLineResponse> data) {
		this.data = data;
	}

}
		