package com.cjgmj.jboss.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cjgmj.jboss.entity.Cliente;
import com.cjgmj.jboss.entity.Factura;
import com.cjgmj.jboss.entity.Producto;

public interface IClienteService {

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public Cliente findOne(Long id);

	public void save(Cliente cliente);

	public void delete(Long id);

	public List<Producto> findByNombre(String term);

	public void saveFactura(Factura factura);

	public Producto findProductoById(Long id);

	public Factura findFacturaById(Long id);

	public void deleteFactura(Long id);

	public Factura fetchFacturaById(Long id);

	public Cliente fetchClienteById(Long id);

}
