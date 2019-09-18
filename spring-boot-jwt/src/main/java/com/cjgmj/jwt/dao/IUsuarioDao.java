package com.cjgmj.jwt.dao;

import org.springframework.data.repository.CrudRepository;

import com.cjgmj.jwt.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
