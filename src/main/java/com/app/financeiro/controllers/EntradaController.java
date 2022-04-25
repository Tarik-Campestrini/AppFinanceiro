package com.app.financeiro.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.financeiro.models.Entrada;
import com.app.financeiro.models.Mes;
import com.app.financeiro.models.Usuario;
import com.app.financeiro.repository.EntradaRepository;
import com.app.financeiro.repository.MesRepository;
import com.app.financeiro.repository.UsuarioRepository;

@Controller
public class EntradaController {

	
	private Usuario usuario;
	private Mes mes;

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MesRepository mesRepository;

	@ResponseBody
	@RequestMapping(value = "home/entradas", method = RequestMethod.GET)
	public ModelAndView detalhesEntrada(@RequestParam("mes") long id_mes) {

		buscarUsuarioLogado();
		mes = mesRepository.buscarMesSelecionado(id_mes);		
		long mes_id = id_mes;
		long usuario_id = usuario.getId();
		
		ModelAndView mv = new ModelAndView("entrada/detalhesEntrada");	
		mv.addObject("usuario", usuario);
		mv.addObject("mes", mes);
		mv.addObject("entrada", entradaRepository.buscarEntradaIdUsuario(usuario_id, mes_id));
			
		
		return mv;

	}

	public void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioEmail(email).get(0);
		}

	}

	@RequestMapping(value = "home/salvarEntrada", method = RequestMethod.POST)
	public String salvarEntrada(Entrada entrada) {

		entrada.setUsuario(usuario);
		entrada.setMes(mes);
		entradaRepository.save(entrada);

		return "redirect:/home/entradas/?mes=" + mes.getId();

	}

	@GetMapping("home/listarEntrada")
	@ResponseBody
	public Optional<Entrada> buscarEntrada(long id) {
		System.out.print("este e o id: ");
		return entradaRepository.findById(id);

	}

	@GetMapping("home/deletarEntrada/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("redirect:/home/entradas/?mes=" + mes.getId());
		Optional<Entrada> entrada = entradaRepository.findById(id);
		entradaRepository.delete(entrada.get());

		return mv;
	}
	
	
		
	
}
