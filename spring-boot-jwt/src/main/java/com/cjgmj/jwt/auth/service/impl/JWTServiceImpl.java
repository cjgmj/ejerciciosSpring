package com.cjgmj.jwt.auth.service.impl;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.cjgmj.jwt.auth.service.JWTService;

import io.jsonwebtoken.Claims;

public class JWTServiceImpl implements JWTService {

	@Override
	public String create(Authentication auth) {
		return null;
	}

	@Override
	public boolean validate(String token) {
		return false;
	}

	@Override
	public Claims getClaims(String token) {
		return null;
	}

	@Override
	public String getUsername(String token) {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(String token) {
		return null;
	}

	@Override
	public String resolve(String token) {
		return null;
	}

}
