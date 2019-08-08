package com.cjgmj.datajpa.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cjgmj.datajpa.entity.Cliente;
import com.cjgmj.datajpa.service.IClienteService;

@Controller
// Para no tener un campo oculto con el id del cliente en el formulario, guardamos el cliente en la sesión hasta que se realizar el registro
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		String mensajeFlash = (cliente.getId() != null) ? "Cliente modificado con éxito" : "Cliente creado con éxito";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser 0");
			return "redirect:/listar";
		}
		model.put("titulo", "Editar Cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:/listar";
	}

}
