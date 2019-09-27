package com.cjgmj.tomcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjgmj.tomcat.service.IClienteService;
import com.cjgmj.tomcat.view.xml.ClienteList;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	// Por defecto va a devolver XML, para obtener el JSON incluir en la URL
	// ?format=json
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/listar")
	public ClienteList listar() {
		return new ClienteList(clienteService.findAll());
	}
}
