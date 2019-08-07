package com.cjgmj.datajpa.dao;

import java.util.List;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();

	public void save(Cliente cliente);

	public Cliente findOne(Long id);

	public void delete(Long id);

}
