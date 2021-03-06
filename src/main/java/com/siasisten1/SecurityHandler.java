package com.siasisten1;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException  {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		System.out.println("ROLEX :"+roles.contains("ROLE_ADMIN"));
		if (roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/pengajuan/viewall");
		}

		System.out.println("ROLEX :"+roles.contains("ROLE_USER"));
		if (roles.contains("ROLE_USER")) {
			response.sendRedirect("/pengajuan/viewall");
		}

	}
}