package com.cjgmj.jboss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjgmj.jboss.dao.IUsuarioDao;
import com.cjgmj.jboss.entity.Role;
import com.cjgmj.jboss.entity.Usuario;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	private static final Logger LOG = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			LOG.error("No se ha encontrado el usuario " + username);
			throw new UsernameNotFoundException("No existe el usuario");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role : usuario.getRoles()) {
			LOG.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		if (authorities.isEmpty()) {
			LOG.error("El usuario " + username + " no tiene roles asignados");
			throw new UsernameNotFoundException("El usuario no tiene roles asignados");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
