package com.cjgmj.jwt.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!requiresAuthentication(request.getHeader("Authorization"))) {
			chain.doFilter(request, response);
			return;
		}

	}

	protected boolean requiresAuthentication(String header) {
		if (header == null || !header.toLowerCase().startsWith("Bearer ")) {
			return false;
		}
		return true;
	}

}
