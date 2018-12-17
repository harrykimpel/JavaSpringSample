
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

import com.newrelic.automotive.dao.InvLineDAO;
import com.newrelic.automotive.service.InvLineService;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.InvLineResponse;
import com.newrelic.momentum.exception.MomentumException;

/**
 * AbstractInvLineService contains the default implementation of methods.
 * @author Ciber Momentum
 */
 
@Transactional
@Service("InvLineServiceImpl")
public abstract class AbstractInvLineServiceImpl implements InvLineService {
	
    private static final Logger logger = LoggerFactory.getLogger(AbstractInvLineServiceImpl.class);
	
	@Autowired
    private InvLineDAO invLineDAO;
       
    /**
     * Provides the List of InvLineResponse
     * @return List of InvLineResponse
     */
     @Override
    public List<InvLineResponse> getAllInvLines() throws MomentumException {
        try {
            return invLineDAO.getAllInvLines();
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
    }    
    
    /**
     * Method to add InvLine
     * @param invLine
     * @return boolean
     */
     @Override
    public boolean addInvLine(InvLine invLine) throws MomentumException {
		boolean bAddSuccess = false;
        try {
             bAddSuccess = invLineDAO.addInvLine(invLine);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bAddSuccess;
    }
	
	/**
     * Method to update InvLine
     * @param invLine
     * @return boolean
     */
    @Override
    public boolean updateInvLine(InvLine invLine) throws MomentumException {
		boolean bUpdateSuccess = false;
        try {
             bUpdateSuccess = invLineDAO.updateInvLine(invLine);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bUpdateSuccess;
    }
    
    /**
     * Method to delete InvLine by Primary Key
     * @param invLine
     * @return boolean
     */
    @Override
    public boolean deleteInvLine(InvLine invLine) throws MomentumException {
		boolean bDeleteSuccess = false;
        try {
             bDeleteSuccess = invLineDAO.deleteInvLine(invLine);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bDeleteSuccess;
    }    
	
	/**
     * Method to get list of InvLines by Not Null properties of InvLine used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invLine
     * @return List<InvLineResponse>
     */
    @Override
    public List<InvLineResponse> getInvLine(InvLine invLine) throws MomentumException {
		try {
			return invLineDAO.getInvLine(invLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}	
    }
    
}
