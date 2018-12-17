

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

@XmlRootElement(name = "invLine")
@Entity
@Table(name = "InvLine")
public class InvLine implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	public InvLine(){
	}
	

	
	@Column(name = "ProdCode", nullable = true)
	private String prodCode;

	public String getProdCode() {
		return this.prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}   

	
	@Column(name = "Quanity", nullable = true)
	private String quanity;

	public String getQuanity() {
		return this.quanity;
	}

	public void setQuanity(String quanity) {
		this.quanity = quanity;
	}   

	
	@Column(name = "UnitPrice", nullable = true)
	private String unitPrice;

	public String getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}   

	
	@Column(name = "Amount", nullable = true)
	private String amount;

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}   
	@Id
	@Column(name = "LineId", unique = true, nullable = false)
	private long lineId;

	public long getLineId() {
		return this.lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}	

	
	@Column(name = "InvNumber", nullable = true)
	private long invNumber;

	public long getInvNumber() {
		return this.invNumber;
	}

	public void setInvNumber(long invNumber) {
		this.invNumber = invNumber;
	}   

}