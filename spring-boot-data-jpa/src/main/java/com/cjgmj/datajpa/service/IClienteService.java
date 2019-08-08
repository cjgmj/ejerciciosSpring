package com.cjgmj.datajpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public Cliente findOne(Long id);

	public void save(Cliente cliente);

	public void delete(Long id);

}
