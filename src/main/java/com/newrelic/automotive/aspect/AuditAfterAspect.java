	
/*
* Copyright ©2016, Ciber Inc All Rights Reserved
*/


package com.newrelic.automotive.aspect;

import java.util.Collection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;

import com.newrelic.momentum.annotation.Auditable;
import com.newrelic.momentum.entity.Audit;
import com.newrelic.momentum.service.AuditService;
import com.newrelic.momentum.exception.MomentumException;

import com.newrelic.api.agent.NewRelic;

/**
 * AuditAfterAspect Class
 * @author Ciber Momentum Team
 */
 
@Aspect
public class AuditAfterAspect {
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private HttpServletRequest request;
	
	@After("execution(* com.newrelic.automotive.controller.*.*(..)) && @annotation(auditable)")
	public void auditAspect(JoinPoint jp, Auditable auditable) throws MomentumException{
		String loggedInUserIPAddress = request.getRemoteAddr();
		
		String requestId = request.getHeader("X-HARRY-ID");
		NewRelic.addCustomParameter ("HarryID", requestId);
		NewRelic.addCustomParameter ("HarryStatic", "static test");

		HttpSession session = request.getSession();
		String loggedInUser = null;
		if(session.getAttribute("username") != null){
			loggedInUser = session.getAttribute("username").toString();
		}
		
		Collection<? extends GrantedAuthority> authoritiesList = new ArrayList<>();
		if(authoritiesList != null && authoritiesList.size() > 0){
			authoritiesList = (Collection<? extends GrantedAuthority>) session.getAttribute("authorities");
		}
		
		String  grantedAuthority = null;
		for(GrantedAuthority ga : authoritiesList) {
			grantedAuthority = ga.getAuthority();
		}
		
		Audit audit = new Audit();
		audit.setUserName(loggedInUser);
		audit.setUserRole(grantedAuthority);
		audit.setActionType(auditable.value());
		audit.setUserIP(loggedInUserIPAddress);
		
		
		auditService.addAudit(audit);		
	}	
	
}
