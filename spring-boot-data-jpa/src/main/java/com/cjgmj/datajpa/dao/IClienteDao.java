package com.cjgmj.datajpa.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cjgmj.datajpa.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.facturas WHERE c.id = :idCliente")
	public Cliente fetchByIdWithFacturas(Long idCliente);

}
