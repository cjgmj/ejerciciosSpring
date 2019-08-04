package com.cjgmj.di.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cjgmj.di.services.IndexService;

@Controller
public class IndexController {

	private IndexService indexService;

	// Al inyectar por constructor se puede omitir el Autowired
	public IndexController(IndexService indexService) {
		this.indexService = indexService;
	}

	@GetMapping({ "/", "", "/index" })
	public String index(Model model) {
		model.addAttribute("objeto", indexService.operacion());
		return "index";
	}

}
