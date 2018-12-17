package com.newrelic.momentum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newrelic.momentum.dao.AuditDAO;
import com.newrelic.momentum.entity.Audit;

@Transactional
@Service("auditService")
public class AuditService {

	@Autowired
	private AuditDAO auditDAO;
	
	Logger log = LoggerFactory.getLogger(AuditService.class);
	
	public Boolean addAudit(Audit audit){
		return auditDAO.addAudit(audit);
	}
	
}
