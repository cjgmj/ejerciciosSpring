package com.cjgmj.datajpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.cjgmj.datajpa.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
