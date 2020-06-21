package com.cjgmj.form.services;

import java.util.List;

import com.cjgmj.form.models.domain.Pais;

public interface PaisService {

	List<Pais> listar();

	Pais obtenerPorId(Integer id);

}
