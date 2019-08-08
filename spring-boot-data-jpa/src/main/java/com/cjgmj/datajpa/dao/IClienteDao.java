package com.cjgmj.datajpa.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
