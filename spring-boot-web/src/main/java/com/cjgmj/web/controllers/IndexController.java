package com.cjgmj.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjgmj.web.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping({ "/index", "/", "", "/home" })
	public String index(Model model) {
		model.addAttribute("titulo", "Hola Spring Framework");
		return "index";
	}

	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Prueba nombre");
		usuario.setApellido("Prueba apellido");

		model.addAttribute("titulo", "Perfil del usuario: ".concat(usuario.getNombre()));
		model.addAttribute("usuario", usuario);
		return "perfil";
	}

}
