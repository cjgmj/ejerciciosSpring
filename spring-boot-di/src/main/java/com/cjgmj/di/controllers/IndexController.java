package com.cjgmj.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cjgmj.di.services.IndexService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;

	@GetMapping({ "/", "", "index" })
	public String index(Model model) {
		model.addAttribute("objeto", indexService.operacion());
		return "index";
	}

}
