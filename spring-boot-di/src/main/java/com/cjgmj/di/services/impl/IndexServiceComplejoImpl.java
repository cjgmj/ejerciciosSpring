package com.cjgmj.di.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.cjgmj.di.services.IndexService;

@Component("indexServiceComplejo")
// Indica que es el servicio que va a inyectar por defecto
@Primary
public class IndexServiceComplejoImpl implements IndexService {

	@Override
	public String operacion() {
		return "Ejecutando proceso complejo";
	}

}
