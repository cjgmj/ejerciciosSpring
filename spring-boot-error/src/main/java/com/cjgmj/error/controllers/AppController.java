package com.cjgmj.error.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cjgmj.error.models.Usuario;
import com.cjgmj.error.service.UsuarioService;

@Controller
public class AppController {

	@Autowired
	private UsuarioService usuarioService;

	@SuppressWarnings("unused")
	@GetMapping("/index")
	public String index() {
//		final Integer valor = 100 / 0;
		final Integer valor = Integer.parseInt("10x");
		return "index";
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id, Model model) {
		final Usuario usuario = this.usuarioService.obtenerPorId(id);

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));

		return "ver";
	}

}
