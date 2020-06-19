package com.cjgmj.form.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjgmj.form.models.domain.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		final Usuario usuario = new Usuario();
		usuario.setIdentificador("THR3");
		usuario.setNombre("John");
		usuario.setApellidos("Doe");

		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);

		return "form";
	}

	// BindingResult contiene los mensajes de error de la validación, en caso de
	// haber errores, se inyecta de forma automática al aparecer la anotación
	// @Valid. Debe estar justo después del objeto a validar
	// @ModelAttribute indica el nombre con el que el atributo será pasado a la
	// vista en caso de que falle la validación
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
		model.addAttribute("titulo", "Resultado form");

		if (result.hasErrors()) {
			return "form";
		}

		model.addAttribute("usuario", usuario);

		return "resultado";
	}

}
