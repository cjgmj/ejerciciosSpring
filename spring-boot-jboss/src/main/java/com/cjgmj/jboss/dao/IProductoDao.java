package com.cjgmj.jboss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cjgmj.jboss.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

	@Query("select p from Producto p where p.nombre like %:term%")
	public List<Producto> findByNombre(String term);

	public List<Producto> findByNombreLikeIgnoreCase(String term);

}
