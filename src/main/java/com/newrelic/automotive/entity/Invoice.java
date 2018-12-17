

/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "invoice")
@Entity
@Table(name = "Invoice")
public class Invoice implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	public Invoice(){
	}
	
	@Id
	@Column(name = "InvNumber", unique = true, nullable = false)
	private long invNumber;

	public long getInvNumber() {
		return this.invNumber;
	}

	public void setInvNumber(long invNumber) {
		this.invNumber = invNumber;
	}	

	@Temporal(TemporalType.DATE)
	@Column(name = "InvDate", nullable = true)
	private Date invDate;

	public Date getInvDate() {
		return this.invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}   

	
	@Column(name = "CustNumber", nullable = true)
	private String custNumber;

	public String getCustNumber() {
		return this.custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}   

	
	@Column(name = "InvPoNumber", nullable = true)
	private String invPoNumber;

	public String getInvPoNumber() {
		return this.invPoNumber;
	}

	public void setInvPoNumber(String invPoNumber) {
		this.invPoNumber = invPoNumber;
	}   

	
	@Column(name = "InvTotal", nullable = true, precision = 8)
	private BigDecimal invTotal;

	public BigDecimal getInvTotal() {
		return this.invTotal;
	}

	public void setInvTotal(BigDecimal invTotal) {
		this.invTotal = invTotal;
	}   

}