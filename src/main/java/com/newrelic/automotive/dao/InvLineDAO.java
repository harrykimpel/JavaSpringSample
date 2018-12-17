
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.dao;

import java.util.List;
import com.newrelic.automotive.entity.InvLine;
import com.newrelic.automotive.response.InvLineResponse;
import com.newrelic.momentum.exception.MomentumException;

public interface InvLineDAO {
	
	/**
     * Provides the List of InvLineResponse
     * @return List of InvLineResponse
     */
	public List<InvLineResponse> getAllInvLines() throws MomentumException;
	
	/**
     * Method to add InvLine
     * @param invLine
     * @return boolean
     */
	public boolean addInvLine(InvLine invLine) throws MomentumException;

	/**
     * Method to update InvLine
     * @param invLine
     * @return boolean
     */
	public boolean updateInvLine(InvLine invLine) throws MomentumException;

	/**
     * Method to delete InvLine by Primary Key
     * @param invLine
     * @return boolean
     */
	public boolean deleteInvLine(InvLine invLine) throws MomentumException;
	
	 /**
     * Method to get list of InvLines by Not Null properties of InvLine used for search criteria.
     * Only primitive properties are considered for search criteria.
     * @param invLine
     * @return List<InvLineResponse>
     */
	public List<InvLineResponse> getInvLine(InvLine invLine) throws MomentumException;
    
		

}