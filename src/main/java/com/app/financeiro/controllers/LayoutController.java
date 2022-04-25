package com.app.financeiro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class LayoutController {
	
	@GetMapping("home/layoutDark")
	public ModelAndView layoutDark() {
		ModelAndView mv = new ModelAndView("layout/layout-static");
		return mv;
	}

	@GetMapping("home/layoutLight")
	public ModelAndView layoutLight() {
		ModelAndView mv = new ModelAndView("layout/layout-sidenav-light");
		return mv;
	}
}
