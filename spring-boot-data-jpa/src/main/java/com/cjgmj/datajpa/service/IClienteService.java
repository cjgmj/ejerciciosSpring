package com.cjgmj.datajpa.service;

import java.util.List;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findOne(Long id);

	public void save(Cliente cliente);

	public void delete(Long id);

}
