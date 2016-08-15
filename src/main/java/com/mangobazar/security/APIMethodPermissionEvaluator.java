package com.mangobazar.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class APIMethodPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		if (!authentication.isAuthenticated())
			return false;
		
		Collection<SimpleGrantedAuthority> authList = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
		for(SimpleGrantedAuthority grant:authList)
		 if( grant.getAuthority().equals(targetDomainObject.getClass().getName() + "." +
				 permission.toString()))
			 return true;
		 
		return false;

	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
