
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newrelic.automotive.dao.CustomerDAO;
import com.newrelic.automotive.service.CustomerService;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.CustomerResponse;
import com.newrelic.momentum.exception.MomentumException;

/**
 * AbstractCustomerService contains the default implementation of methods.
 * @author Ciber Momentum
 */
 
@Transactional
@Service("CustomerServiceImpl")
public abstract class AbstractCustomerServiceImpl implements CustomerService {
	
    private static final Logger logger = LoggerFactory.getLogger(AbstractCustomerServiceImpl.class);
	
	@Autowired
    private CustomerDAO customerDAO;
       
    /**
     * Provides the List of CustomerResponse
     * @return List of CustomerResponse
     */
     @Override
    public List<CustomerResponse> getAllCustomers() throws MomentumException {
        try {
            return customerDAO.getAllCustomers();
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
    }    
    
    /**
     * Method to add Customer
     * @param customer
     * @return boolean
     */
     @Override
    public boolean addCustomer(Customer customer) throws MomentumException {
		boolean bAddSuccess = false;
        try {
             bAddSuccess = customerDAO.addCustomer(customer);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bAddSuccess;
    }
	
	/**
     * Method to update Customer
     * @param customer
     * @return boolean
     */
    @Override
    public boolean updateCustomer(Customer customer) throws MomentumException {
		boolean bUpdateSuccess = false;
        try {
             bUpdateSuccess = customerDAO.updateCustomer(customer);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bUpdateSuccess;
    }
    
    /**
     * Method to delete Customer by Primary Key
     * @param customer
     * @return boolean
     */
    @Override
    public boolean deleteCustomer(Customer customer) throws MomentumException {
		boolean bDeleteSuccess = false;
        try {
             bDeleteSuccess = customerDAO.deleteCustomer(customer);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bDeleteSuccess;
    }    
	
	/**
     * Method to get list of Customers by Not Null properties of Customer used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param customer
     * @return List<CustomerResponse>
     */
    @Override
    public List<CustomerResponse> getCustomer(Customer customer) throws MomentumException {
		try {
			return customerDAO.getCustomer(customer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}	
    }
    
}
