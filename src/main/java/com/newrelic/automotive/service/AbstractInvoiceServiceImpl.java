
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

import com.newrelic.automotive.dao.InvoiceDAO;
import com.newrelic.automotive.service.InvoiceService;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.exception.UnknownResourceException;
import com.newrelic.automotive.response.InvoiceResponse;
import com.newrelic.momentum.exception.MomentumException;

/**
 * AbstractInvoiceService contains the default implementation of methods.
 * @author Ciber Momentum
 */
 
@Transactional
@Service("InvoiceServiceImpl")
public abstract class AbstractInvoiceServiceImpl implements InvoiceService {
	
    private static final Logger logger = LoggerFactory.getLogger(AbstractInvoiceServiceImpl.class);
	
	@Autowired
    private InvoiceDAO invoiceDAO;
       
    /**
     * Provides the List of InvoiceResponse
     * @return List of InvoiceResponse
     */
     @Override
    public List<InvoiceResponse> getAllInvoices() throws MomentumException {
        try {
            return invoiceDAO.getAllInvoices();
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
    }    
    
    /**
     * Method to add Invoice
     * @param invoice
     * @return boolean
     */
     @Override
    public boolean addInvoice(Invoice invoice) throws MomentumException {
		boolean bAddSuccess = false;
        try {
             bAddSuccess = invoiceDAO.addInvoice(invoice);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bAddSuccess;
    }
	
	/**
     * Method to update Invoice
     * @param invoice
     * @return boolean
     */
    @Override
    public boolean updateInvoice(Invoice invoice) throws MomentumException {
		boolean bUpdateSuccess = false;
        try {
             bUpdateSuccess = invoiceDAO.updateInvoice(invoice);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bUpdateSuccess;
    }
    
    /**
     * Method to delete Invoice by Primary Key
     * @param invoice
     * @return boolean
     */
    @Override
    public boolean deleteInvoice(Invoice invoice) throws MomentumException {
		boolean bDeleteSuccess = false;
        try {
             bDeleteSuccess = invoiceDAO.deleteInvoice(invoice);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return bDeleteSuccess;
    }    
	
	/**
     * Method to get list of Invoices by Not Null properties of Invoice used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invoice
     * @return List<InvoiceResponse>
     */
    @Override
    public List<InvoiceResponse> getInvoice(Invoice invoice) throws MomentumException {
		try {
			return invoiceDAO.getInvoice(invoice);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}	
    }
    
}
