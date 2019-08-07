package com.cjgmj.datajpa.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjgmj.datajpa.dao.IClienteDao;
import com.cjgmj.datajpa.entity.Cliente;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario de Cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	// Siempre debe ir el objeto seguido del BindingResult.
	// Pasa automáticamente el objeto si la clase se llama igual al atributo en la
	// vista, sin tener en cuenta las mayúculas y minúsculas. Si el nombre es
	// distinto de se usar @ModelAttribute indicando el nombre en la vista
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		clienteDao.save(cliente);
		return "redirect:listar";
	}

}
