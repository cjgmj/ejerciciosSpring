package com.cjgmj.form.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cjgmj.form.editors.NombreMayusculaEditor;
import com.cjgmj.form.models.domain.Usuario;
import com.cjgmj.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.validador);

		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellidos", new NombreMayusculaEditor());
	}

	// Con @ModelAttribute se le pasa el contenido del método a la vista
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
	}

	@GetMapping("/form")
	public String form(Model model) {
		final Usuario usuario = new Usuario();
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
	// SessionStatus se usa para completar la sesión y eliminar los objetos que
	// estén en ella
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			SessionStatus status) {
		model.addAttribute("titulo", "Resultado form");

		if (result.hasErrors()) {
			return "form";
		}

		model.addAttribute("usuario", usuario);

		status.setComplete();

		return "resultado";
	}

}
