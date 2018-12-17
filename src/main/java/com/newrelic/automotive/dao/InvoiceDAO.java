
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao;

import java.util.List;
import com.newrelic.automotive.entity.Invoice;
import com.newrelic.automotive.response.InvoiceResponse;
import com.newrelic.momentum.exception.MomentumException;

public interface InvoiceDAO {
	
	/**
     * Provides the List of InvoiceResponse
     * @return List of InvoiceResponse
     */
	public List<InvoiceResponse> getAllInvoices() throws MomentumException;
	
	/**
     * Method to add Invoice
     * @param invoice
     * @return boolean
     */
	public boolean addInvoice(Invoice invoice) throws MomentumException;

	/**
     * Method to update Invoice
     * @param invoice
     * @return boolean
     */
	public boolean updateInvoice(Invoice invoice) throws MomentumException;

	/**
     * Method to delete Invoice by Primary Key
     * @param invoice
     * @return boolean
     */
	public boolean deleteInvoice(Invoice invoice) throws MomentumException;
	
	 /**
     * Method to get list of Invoices by Not Null properties of Invoice used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invoice
     * @return List<InvoiceResponse>
     */
	public List<InvoiceResponse> getInvoice(Invoice invoice) throws MomentumException;
    
		

}