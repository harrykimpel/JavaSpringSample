

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

import com.newrelic.automotive.entity.*;


public class InvLineResponse implements Serializable{
	 
	private static final long serialVersionUID = 1L;
		
	
	private String prodCode;

	public String getProdCode() {
		return this.prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}   	
	
	private String quanity;

	public String getQuanity() {
		return this.quanity;
	}

	public void setQuanity(String quanity) {
		this.quanity = quanity;
	}   	
	
	private String unitPrice;

	public String getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}   	
	
	private String amount;

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}   
	
	private long lineId;

	public long getLineId() {
		return this.lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}		
	
	private long invNumber;

	public long getInvNumber() {
		return this.invNumber;
	}

	public void setInvNumber(long invNumber) {
		this.invNumber = invNumber;
	}   

}