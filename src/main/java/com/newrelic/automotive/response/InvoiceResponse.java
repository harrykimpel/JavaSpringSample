

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


public class InvoiceResponse implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	
	private long invNumber;

	public long getInvNumber() {
		return this.invNumber;
	}

	public void setInvNumber(long invNumber) {
		this.invNumber = invNumber;
	}		
	
	private Date invDate;

	public Date getInvDate() {
		return this.invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}   	
	
	private String custNumber;

	public String getCustNumber() {
		return this.custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}   	
	
	private String invPoNumber;

	public String getInvPoNumber() {
		return this.invPoNumber;
	}

	public void setInvPoNumber(String invPoNumber) {
		this.invPoNumber = invPoNumber;
	}   	
	
	private BigDecimal invTotal;

	public BigDecimal getInvTotal() {
		return this.invTotal;
	}

	public void setInvTotal(BigDecimal invTotal) {
		this.invTotal = invTotal;
	}   

}