package com.cjgmj.datajpa.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cjgmj.datajpa.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

	@Query("SELECT f FROM Factura f JOIN FETCH f.cliente JOIN FETCH f.items l JOIN FETCH l.producto WHERE f.id = :idFactura")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long idFactura);

}
