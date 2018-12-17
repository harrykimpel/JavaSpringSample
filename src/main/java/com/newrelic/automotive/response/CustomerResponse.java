

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


public class CustomerResponse implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	
	private String customerNumber;

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}		
	
	private String firstName;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   	
	
	private String lastName;

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   	
	
	private String address;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   	
	
	private String city;

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   	
	
	private String state;

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   	
	
	private String zip;

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}   	
	
	private String model;

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}   	
	
	private String color;

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}   	
	
	private String vin;

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}   

}