package com.newrelic.momentum.dao.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newrelic.momentum.dao.AuditDAO;
import com.newrelic.momentum.entity.Audit;

@Repository
public class AuditDAOImpl implements AuditDAO{
	
	Logger log = LoggerFactory.getLogger(AuditDAOImpl.class);
	
	@Autowired
    protected SessionFactory sessionFactory;

	@Override
	public Boolean addAudit(Audit audit) {
		
		Audit tmpAudit = new Audit();
		tmpAudit.setUserName(audit.getUserName());
		tmpAudit.setUserRole(audit.getUserRole());
		tmpAudit.setUserIP(audit.getUserIP());
		tmpAudit.setActionType(audit.getActionType());
		tmpAudit.setCreatedDate(new Date());
		boolean auditSuccess = true;
		
		try{
			sessionFactory.getCurrentSession().save(tmpAudit);
			log.info("Audit Operation is successfull");
		}catch(HibernateException hbe){
			log.error("Audit Operation Failed ::: ", hbe);
			auditSuccess = false;
		}		
		return auditSuccess;
	}

}
