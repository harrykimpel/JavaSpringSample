
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

import com.newrelic.automotive.dao.InvoiceDAO;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.entity.*;
import com.newrelic.automotive.response.InvoiceResponse;
import com.newrelic.momentum.exception.MomentumException;


/**
 * Abstract Invoice DAOImpl contains the default implementation of the InvoiceDAO.
 * @author Ciber Momentum
 */
 
@Repository
public abstract class AbstractInvoiceDAOImpl implements InvoiceDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbstractInvoiceDAOImpl.class);
	
	@Autowired
    protected SessionFactory sessionFactory;
    
    public Session getCurrentSession() throws MomentumException {
        return sessionFactory.getCurrentSession();
    }
           
    @SuppressWarnings("unchecked")
	/**
     * Provides the List of InvoiceResponse
     * @return List of InvoiceResponse
     */
	@Override
	public List<InvoiceResponse> getAllInvoices() throws MomentumException {
		List<InvoiceResponse> invoiceResponse = new ArrayList<>();
		try {
			List<Invoice> result = getCurrentSession().createQuery("from Invoice").list();
			if (result != null){
				invoiceResponse = readInvoiceDetails(result);
			}
		} catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return invoiceResponse;
	}
	
    /**
     * Method to add Invoice
     * @param invoice
     * @return boolean
     */
	@Transactional(readOnly = false)
	@Override
    public boolean addInvoice(Invoice invoice) throws MomentumException {
		boolean message = false;
        try {
                	
			getCurrentSession().save(invoice);
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
     * Method to update Invoice
     * @param invoice
     * @return boolean
     */
	@Override
    public boolean updateInvoice(Invoice invoice) throws MomentumException {
		boolean message = false;
        try{
            getCurrentSession().update(invoice);
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
     * Method to delete Invoice by Primary Key
     * @param invoice
     * @return boolean
     */
	@Override
    public boolean deleteInvoice(Invoice invoice) throws MomentumException {
        boolean message = false;
        try {
                    
            Invoice invoiceObj = (Invoice) getCurrentSession().load(Invoice.class, invoice.getInvNumber());
         
            getCurrentSession().delete(invoiceObj);
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
     * Helper method to convert Invoice to InvoiceResponse 
     * @param invoiceList
     * @return
     */
    private List<InvoiceResponse> readInvoiceDetails(List<Invoice> invoiceList) throws MomentumException  {
    
        List<InvoiceResponse> newInvoiceList = new ArrayList<>();
        if (invoiceList == null) {
            return newInvoiceList;
        }

        try {
            for (Invoice invoice : invoiceList) {
            	InvoiceResponse invoiceResponse = new InvoiceResponse();
            	String ignoreProperties [];
            	Map<String, Object> propertyNameValuesMap = getBeanProperties(invoice, true, true);
            	Set<String> propertiesToIgnoreSet = new HashSet<>();
                if (propertyNameValuesMap != null) {
                	propertiesToIgnoreSet = propertyNameValuesMap.keySet();
                }
                ignoreProperties = propertiesToIgnoreSet.toArray(new String[propertiesToIgnoreSet.size()]);
            	BeanUtils.copyProperties(invoice, invoiceResponse, ignoreProperties);
                
                newInvoiceList.add(invoiceResponse);
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
        return newInvoiceList;
    }   
    
    
    /**
     * Method to get list of Invoices by Not Null properties of Invoice used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invoice
     * @return List<InvoiceResponse>
     */
	@Override
    public List<InvoiceResponse> getInvoice(Invoice invoice) throws MomentumException {
        List<InvoiceResponse> invoiceResponseList = new ArrayList<>(); 
        List<Invoice> invoiceList = new ArrayList<>();
        try {        
        	Criteria cr = getCurrentSession().createCriteria(Invoice.class);
        	Map<String, Object> propertyNameValuesMap = getBeanProperties(invoice, false, false);
            if (propertyNameValuesMap != null) {
                cr.add(Restrictions.allEq(propertyNameValuesMap));
                invoiceList = cr.list();
                if (invoiceList != null){
    				invoiceResponseList = readInvoiceDetails(invoiceList);
    			}
            }
           } catch (HibernateException hbe) {
				logger.error(hbe.getMessage(), hbe);
				throw hbe;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
        return invoiceResponseList;
    }
    
    /**
     * Method to get the Invoice by Primary key
     */
	public Invoice getInvoiceByPrimaryKey(long invNumber) throws MomentumException {
        Invoice invoice = null;
        try {
            invoice = ( Invoice) getCurrentSession().get( Invoice.class, invNumber);
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
        return invoice;
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
					if (!type.isPrimitive() && !"String".equalsIgnoreCase(type.getSimpleName()) && !"BigDecimal".equalsIgnoreCase(type.getSimpleName()) && !"Date".equalsIgnoreCase(type.getSimpleName()) && !"InvoiceId".equalsIgnoreCase(type.getSimpleName())) {
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