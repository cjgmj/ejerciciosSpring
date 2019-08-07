package com.cjgmj.datajpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
