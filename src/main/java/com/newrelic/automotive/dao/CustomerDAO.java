
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao;

import java.util.List;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.response.CustomerResponse;
import com.newrelic.momentum.exception.MomentumException;

public interface CustomerDAO {
	
	/**
     * Provides the List of CustomerResponse
     * @return List of CustomerResponse
     */
	public List<CustomerResponse> getAllCustomers() throws MomentumException;
	
	/**
     * Method to add Customer
     * @param customer
     * @return boolean
     */
	public boolean addCustomer(Customer customer) throws MomentumException;

	/**
     * Method to update Customer
     * @param customer
     * @return boolean
     */
	public boolean updateCustomer(Customer customer) throws MomentumException;

	/**
     * Method to delete Customer by Primary Key
     * @param customer
     * @return boolean
     */
	public boolean deleteCustomer(Customer customer) throws MomentumException;
	
	 /**
     * Method to get list of Customers by Not Null properties of Customer used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param customer
     * @return List<CustomerResponse>
     */
	public List<CustomerResponse> getCustomer(Customer customer) throws MomentumException;
    
		

}