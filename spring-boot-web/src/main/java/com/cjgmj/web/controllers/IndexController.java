package com.cjgmj.web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		usuario.setEmail("prueba@prueba.com");

		model.addAttribute("titulo", "Perfil del usuario: ".concat(usuario.getNombre()));
		model.addAttribute("usuario", usuario);
		return "perfil";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Usuario> usuarios = Arrays.asList(
				new Usuario("Prueba nombre 1", "Prueba apellido 1", "prueba1@prueba.com"),
				new Usuario("Prueba nombre 2", "Prueba apellido 2", "prueba2@prueba.com"),
				new Usuario("Prueba nombre 3", "Prueba apellido 3", "prueba3@prueba.com"),
				new Usuario("Prueba nombre 4", "Prueba apellido 4", "prueba4@prueba.com"));

		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		return "listar";
	}
}
