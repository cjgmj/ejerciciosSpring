package com.cjgmj.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	// Se reinician los parámetros del request, cambia la URL
	@GetMapping("/")
	public String home() {
		return "redirect:/app/index";
	}

	// No se reinician los parámetros del request, no cambia la URL
	@GetMapping("/forward")
	public String homeForward() {
		return "forward:/app/index";
	}

	@GetMapping("/textoRedirect")
	public String textoRedirect(@RequestParam String texto, Model model) {
		model.addAttribute("resultado", "El texto enviado es: " + texto);
		return "redirect:/params/string";
	}

	@GetMapping("/textoForward")
	public String textoForward(@RequestParam String texto, Model model) {
		model.addAttribute("resultado", "El texto enviado es: " + texto);
		return "forward:/params/string";
	}

	@GetMapping("/google")
	public String google() {
		return "redirect:https://www.google.com";
	}

}
