
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

import com.newrelic.automotive.dao.InvLineDAO;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.entity.*;
import com.newrelic.automotive.response.InvLineResponse;
import com.newrelic.momentum.exception.MomentumException;


/**
 * Abstract InvLine DAOImpl contains the default implementation of the InvLineDAO.
 * @author Ciber Momentum
 */
 
@Repository
public abstract class AbstractInvLineDAOImpl implements InvLineDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbstractInvLineDAOImpl.class);
	
	@Autowired
    protected SessionFactory sessionFactory;
    
    public Session getCurrentSession() throws MomentumException {
        return sessionFactory.getCurrentSession();
    }
           
    @SuppressWarnings("unchecked")
	/**
     * Provides the List of InvLineResponse
     * @return List of InvLineResponse
     */
	@Override
	public List<InvLineResponse> getAllInvLines() throws MomentumException {
		List<InvLineResponse> invLineResponse = new ArrayList<>();
		try {
			List<InvLine> result = getCurrentSession().createQuery("from InvLine").list();
			if (result != null){
				invLineResponse = readInvLineDetails(result);
			}
		} catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return invLineResponse;
	}
	
    /**
     * Method to add InvLine
     * @param invLine
     * @return boolean
     */
	@Transactional(readOnly = false)
	@Override
    public boolean addInvLine(InvLine invLine) throws MomentumException {
		boolean message = false;
        try {
                	
			getCurrentSession().save(invLine);
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
     * Method to update InvLine
     * @param invLine
     * @return boolean
     */
	@Override
    public boolean updateInvLine(InvLine invLine) throws MomentumException {
		boolean message = false;
        try{
            getCurrentSession().update(invLine);
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
     * Method to delete InvLine by Primary Key
     * @param invLine
     * @return boolean
     */
	@Override
    public boolean deleteInvLine(InvLine invLine) throws MomentumException {
        boolean message = false;
        try {
                    
            InvLine invLineObj = (InvLine) getCurrentSession().load(InvLine.class, invLine.getLineId());
         
            getCurrentSession().delete(invLineObj);
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
     * Helper method to convert InvLine to InvLineResponse 
     * @param invLineList
     * @return
     */
    private List<InvLineResponse> readInvLineDetails(List<InvLine> invLineList) throws MomentumException  {
    
        List<InvLineResponse> newInvLineList = new ArrayList<>();
        if (invLineList == null) {
            return newInvLineList;
        }

        try {
            for (InvLine invLine : invLineList) {
            	InvLineResponse invLineResponse = new InvLineResponse();
            	String ignoreProperties [];
            	Map<String, Object> propertyNameValuesMap = getBeanProperties(invLine, true, true);
            	Set<String> propertiesToIgnoreSet = new HashSet<>();
                if (propertyNameValuesMap != null) {
                	propertiesToIgnoreSet = propertyNameValuesMap.keySet();
                }
                ignoreProperties = propertiesToIgnoreSet.toArray(new String[propertiesToIgnoreSet.size()]);
            	BeanUtils.copyProperties(invLine, invLineResponse, ignoreProperties);
                
                newInvLineList.add(invLineResponse);
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
        return newInvLineList;
    }   
    
    
    /**
     * Method to get list of InvLines by Not Null properties of InvLine used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invLine
     * @return List<InvLineResponse>
     */
	@Override
    public List<InvLineResponse> getInvLine(InvLine invLine) throws MomentumException {
        List<InvLineResponse> invLineResponseList = new ArrayList<>(); 
        List<InvLine> invLineList = new ArrayList<>();
        try {        
        	Criteria cr = getCurrentSession().createCriteria(InvLine.class);
        	Map<String, Object> propertyNameValuesMap = getBeanProperties(invLine, false, false);
            if (propertyNameValuesMap != null) {
                cr.add(Restrictions.allEq(propertyNameValuesMap));
                invLineList = cr.list();
                if (invLineList != null){
    				invLineResponseList = readInvLineDetails(invLineList);
    			}
            }
           } catch (HibernateException hbe) {
				logger.error(hbe.getMessage(), hbe);
				throw hbe;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
        return invLineResponseList;
    }
    
    /**
     * Method to get the InvLine by Primary key
     */
	public InvLine getInvLineByPrimaryKey(long lineId) throws MomentumException {
        InvLine invLine = null;
        try {
            invLine = ( InvLine) getCurrentSession().get( InvLine.class, lineId);
        } catch (HibernateException hbe) {
			logger.error(hbe.getMessage(), hbe);
			throw hbe;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
        return invLine;
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
					if (!type.isPrimitive() && !"String".equalsIgnoreCase(type.getSimpleName()) && !"BigDecimal".equalsIgnoreCase(type.getSimpleName()) && !"Date".equalsIgnoreCase(type.getSimpleName()) && !"InvLineId".equalsIgnoreCase(type.getSimpleName())) {
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