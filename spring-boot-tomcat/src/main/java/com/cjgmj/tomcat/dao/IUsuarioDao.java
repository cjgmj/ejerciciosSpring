package com.cjgmj.tomcat.dao;

import org.springframework.data.repository.CrudRepository;

import com.cjgmj.tomcat.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
