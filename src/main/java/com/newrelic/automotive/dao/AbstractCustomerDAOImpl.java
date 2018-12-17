
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newrelic.automotive.dao.CustomerDAO;
import com.newrelic.automotive.entity.Customer;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.entity.*;
import com.newrelic.automotive.response.CustomerResponse;
import com.newrelic.momentum.exception.MomentumException;


/**
 * Abstract Customer DAOImpl contains the default implementation of the CustomerDAO.
 * @author Ciber Momentum
 */
 
@Repository
public abstract class AbstractCustomerDAOImpl implements CustomerDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCustomerDAOImpl.class);
	
	@Autowired
    protected SessionFactory sessionFactory;
    
    public Session getCurrentSession() throws MomentumException {
        return sessionFactory.getCurrentSession();
    }
           
    @SuppressWarnings("unchecked")
	/**
     * Provides the List of CustomerResponse
     * @return List of CustomerResponse
     */
	@Override
	public List<CustomerResponse> getAllCustomers() throws MomentumException {
		List<CustomerResponse> customerResponse = new ArrayList<>();
		try {
			List<Customer> result = getCurrentSession().createQuery("from Customer").list();
			if (result != null){
				customerResponse = readCustomerDetails(result);
			}
		} catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return customerResponse;
	}
	
    /**
     * Method to add Customer
     * @param customer
     * @return boolean
     */
	@Transactional(readOnly = false)
	@Override
    public boolean addCustomer(Customer customer) throws MomentumException {
		boolean message = false;
        try {
                	
			getCurrentSession().save(customer);
			message = true;
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} 
		return message;
    }

	/**
     * Method to update Customer
     * @param customer
     * @return boolean
     */
	@Override
    public boolean updateCustomer(Customer customer) throws MomentumException {
		boolean message = false;
        try{
            getCurrentSession().update(customer);
			message = true;
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	  return message;
    }    

	/**
     * Method to delete Customer by Primary Key
     * @param customer
     * @return boolean
     */
	@Override
    public boolean deleteCustomer(Customer customer) throws MomentumException {
        boolean message = false;
        try {
                    
            Customer customerObj = (Customer) getCurrentSession().load(Customer.class, customer.getCustomerNumber());
         
            getCurrentSession().delete(customerObj);
			message = true;			
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return message;
    }	
    
    /**
     * Helper method to convert Customer to CustomerResponse 
     * @param customerList
     * @return
     */
    private List<CustomerResponse> readCustomerDetails(List<Customer> customerList) throws MomentumException  {
    
        List<CustomerResponse> newCustomerList = new ArrayList<>();
        if (customerList == null) {
            return newCustomerList;
        }

        try {
            for (Customer customer : customerList) {
            	CustomerResponse customerResponse = new CustomerResponse();
            	String ignoreProperties [];
            	Map<String, Object> propertyNameValuesMap = getBeanProperties(customer, true, true);
            	Set<String> propertiesToIgnoreSet = new HashSet<>();
                if (propertyNameValuesMap != null) {
                	propertiesToIgnoreSet = propertyNameValuesMap.keySet();
                }
                ignoreProperties = propertiesToIgnoreSet.toArray(new String[propertiesToIgnoreSet.size()]);
            	BeanUtils.copyProperties(customer, customerResponse, ignoreProperties);
                
                newCustomerList.add(customerResponse);
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
        return newCustomerList;
    }   
    
    
    /**
     * Method to get list of Customers by Not Null properties of Customer used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param customer
     * @return List<CustomerResponse>
     */
	@Override
    public List<CustomerResponse> getCustomer(Customer customer) throws MomentumException {
        List<CustomerResponse> customerResponseList = new ArrayList<>(); 
        List<Customer> customerList = new ArrayList<>();
        try {        
        	Criteria cr = getCurrentSession().createCriteria(Customer.class);
        	Map<String, Object> propertyNameValuesMap = getBeanProperties(customer, false, false);
            if (propertyNameValuesMap != null) {
                cr.add(Restrictions.allEq(propertyNameValuesMap));
                customerList = cr.list();
                if (customerList != null){
    				customerResponseList = readCustomerDetails(customerList);
    			}
            }
           } catch (HibernateException hbe) {
				logger.error(hbe.getMessage(), hbe);
				throw hbe;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
        return customerResponseList;
    }
    
    /**
     * Method to get the Customer by Primary key
     */
	@Transactional
	public Customer getCustomerByPrimaryKey(String customerNumber) throws MomentumException {
        Customer customer = null;
        try {
            customer = ( Customer) getCurrentSession().get( Customer.class, customerNumber);
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
        return customer;
    }

    
    /**
     * Utility method to get all the properties of an object which are primitive types and Not NULL for the search criteria
     */
    private Map<String, Object> getBeanProperties(Object bean, boolean ignorePrimitiveProp, boolean addNotNullProp)  throws MomentumException {    

		Map<String, Object> map = new HashMap<>();
		try {
			BeanInfo info = Introspector.getBeanInfo(bean.getClass(), Object.class);
			PropertyDescriptor[] props = info.getPropertyDescriptors();
			for (PropertyDescriptor pd : props) {
				String name = pd.getName();
				Method getter = pd.getReadMethod();
				Class<?> type = pd.getPropertyType();
				Object value = getter.invoke(bean);
				if(addNotNullProp && ignorePrimitiveProp){
					if (!type.isPrimitive() && !"String".equalsIgnoreCase(type.getSimpleName()) && !"BigDecimal".equalsIgnoreCase(type.getSimpleName()) && !"Date".equalsIgnoreCase(type.getSimpleName()) && !"CustomerId".equalsIgnoreCase(type.getSimpleName())) {
						map.put(name, value);
					}
				}else {
					if ((type.isPrimitive() || "String".equalsIgnoreCase(type.getSimpleName()) || "BigDecimal".equalsIgnoreCase(type.getSimpleName()) || "Date".equalsIgnoreCase(type.getSimpleName())) && value != null) {
						map.put(name, value);
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("Exception occurred while getting the Bean properties ",e);
			throw new MomentumException("Exception Occured while getting the Bean properties -" + e.getMessage());
		}
		return map;
	}	
	
}