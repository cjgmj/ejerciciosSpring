package com.cjgmj.jboss.dao;

import org.springframework.data.repository.CrudRepository;

import com.cjgmj.jboss.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
