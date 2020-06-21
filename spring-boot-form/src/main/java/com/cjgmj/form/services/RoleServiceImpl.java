package com.cjgmj.form.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cjgmj.form.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private List<Role> roles;

	public RoleServiceImpl() {
		this.roles = new ArrayList<>();

		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "Moderador", "ROLE_MODERATOR"));
		this.roles.add(new Role(3, "Usuario", "ROLE_USER"));
	}

	@Override
	public List<Role> listar() {
		return this.roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;

		for (final Role role : this.roles) {
			if (id == role.getId()) {
				resultado = role;
				break;
			}
		}

		return resultado;
	}

}
