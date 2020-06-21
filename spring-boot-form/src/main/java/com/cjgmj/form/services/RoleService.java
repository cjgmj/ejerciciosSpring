package com.cjgmj.form.services;

import java.util.List;

import com.cjgmj.form.models.domain.Role;

public interface RoleService {

	List<Role> listar();

	Role obtenerPorId(Integer id);

}
