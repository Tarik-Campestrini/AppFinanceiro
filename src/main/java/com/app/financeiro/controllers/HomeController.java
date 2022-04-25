package com.app.financeiro.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.financeiro.models.Usuario;
import com.app.financeiro.repository.UsuarioRepository;

@Controller
public class HomeController {
	
	private Usuario usuario;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping("/home")
	public ModelAndView home() {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	public void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if(!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioEmail(email).get(0);
		}
	}

}