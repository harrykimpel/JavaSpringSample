package com.newrelic.momentum.dao;

import com.newrelic.momentum.entity.Audit;

public interface AuditDAO {

	public Boolean addAudit(Audit audit);
	
}
