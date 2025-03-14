/*
package com.teamsparta8.common;

import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null || !authentication.isAuthenticated()){
			return Optional.empty();
		}

		Object principal = authentication.getPrincipal();

		if(principal instanceof UserDetailsImpl userDetails){
			if(userDetails.getUser()!=null && userDetails.getUser().getId() != null){
				return Optional.of(userDetails.getUser().getId().toString());
			}
		}
		return Optional.of(authentication.getName());
	}
}
*/
