package com.cjgmj.di.services.impl;

import org.springframework.stereotype.Component;

import com.cjgmj.di.services.IndexService;

@Component("indexService")
public class IndexServiceImpl implements IndexService {

	@Override
	public String operacion() {
		return "Ejecutando proceso";
	}

}
