package com.cjgmj.form.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cjgmj.form.editors.PaisPropertyEditor;
import com.cjgmj.form.editors.RolesEditor;
import com.cjgmj.form.models.domain.Pais;
import com.cjgmj.form.models.domain.Role;
import com.cjgmj.form.models.domain.Usuario;
import com.cjgmj.form.services.PaisService;
import com.cjgmj.form.services.RoleService;
import com.cjgmj.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolesEditor rolesEditor;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.validador);

		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellidos", new NombreMayusculaEditor());

		binder.registerCustomEditor(Pais.class, "pais", this.paisEditor);
		binder.registerCustomEditor(Role.class, "roles", this.rolesEditor);
	}

	// Con @ModelAttribute se le pasa el contenido del método a la vista
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return this.paisService.listar();
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		final Map<String, String> paises = new HashMap<>();

		paises.put("ES", "España");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");

		return paises;
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		final List<String> roles = new ArrayList<>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");

		return roles;
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		final Map<String, String> roles = new HashMap<>();

		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("generos")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}

	@GetMapping("/form")
	public String form(Model model) {
		final Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellidos("Doe");
		usuario.setHabilitar(Boolean.TRUE);
		usuario.setValorSecreto("*** Valor secreto ***");
		usuario.setPais(new Pais(1, "ES", "España"));
		usuario.setRoles(
				Arrays.asList(new Role(2, "Moderador", "ROLE_MODERATOR"), new Role(3, "Usuario", "ROLE_USER")));

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
