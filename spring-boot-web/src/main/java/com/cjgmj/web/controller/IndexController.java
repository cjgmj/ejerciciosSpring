package com.cjgmj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping({ "/index", "/", "/home" })
	public String index(ModelMap model) {
		model.addAttribute("titulo", "Hola Spring Framework");
		return "index";
	}

}
