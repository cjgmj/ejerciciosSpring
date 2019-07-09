package com.cjgmj.web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjgmj.web.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;

	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;

	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;

	@GetMapping({ "/index", "/", "", "/home" })
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}

	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Prueba nombre");
		usuario.setApellido("Prueba apellido");
		usuario.setEmail("prueba@prueba.com");

		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		model.addAttribute("usuario", usuario);
		return "perfil";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {

		model.addAttribute("titulo", textoListar);
		return "listar";
	}

	// Esta anotación añade el contenido a todos los métodos del controlador, es
	// equivalente a hacer un addAttribute en todos los métodos con el nombre dado
	// en la anotación, en este caso "usuarios".
	@ModelAttribute("usuarios")
	public List<Usuario> usuarios() {
		return Arrays.asList(new Usuario("Prueba nombre 1", "Prueba apellido 1", "prueba1@prueba.com"),
				new Usuario("Prueba nombre 2", "Prueba apellido 2", "prueba2@prueba.com"),
				new Usuario("Prueba nombre 3", "Prueba apellido 3", "prueba3@prueba.com"),
				new Usuario("Prueba nombre 4", "Prueba apellido 4", "prueba4@prueba.com"));
	}
}
