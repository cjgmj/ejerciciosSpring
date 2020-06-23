package com.cjgmj.horario.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Value("${config.horario.apertura}")
	private Integer apertura;

	@Value("${config.horario.cierre}")
	private Integer cierre;

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("titulo", "Bienvenido a atención al cliente");

		return "index";
	}

	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		final StringBuilder mensaje = new StringBuilder("Cerrado, por favor visítenos desde las ");
		mensaje.append(this.apertura);
		mensaje.append(" horas");
		mensaje.append(" hasta las ");
		mensaje.append(this.cierre);
		mensaje.append(" horas. Gracias.");

		model.addAttribute("titulo", "Bienvenido a atención al cliente");
		model.addAttribute("mensaje", mensaje.toString());

		return "cerrado";
	}

}
