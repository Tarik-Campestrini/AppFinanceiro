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

import com.app.financeiro.models.Mes;
import com.app.financeiro.models.Saida;
import com.app.financeiro.models.Usuario;
import com.app.financeiro.repository.MesRepository;
import com.app.financeiro.repository.SaidaRepository;
import com.app.financeiro.repository.UsuarioRepository;

@Controller
public class SaidaController {

	
	private Usuario usuario;
	private Mes mes;

	@Autowired
	private SaidaRepository saidaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MesRepository mesRepository;

	@ResponseBody
	@RequestMapping(value = "home/saidas", method = RequestMethod.GET)
	public ModelAndView detalhesSaida(@RequestParam("mes") long id_mes) {

		buscarUsuarioLogado();
		mes = mesRepository.buscarMesSelecionado(id_mes);		
		long mes_id = id_mes;
		long usuario_id = usuario.getId();
		
		ModelAndView mv = new ModelAndView("saida/detalhesSaida");	
		mv.addObject("usuario", usuario);
		mv.addObject("mes", mes);
		mv.addObject("saida", saidaRepository.buscarSaidaIdUsuario(usuario_id, mes_id));
			
		
		return mv;

	}

	public void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioEmail(email).get(0);
		}

	}


	@RequestMapping(value = "home/salvarSaida", method = RequestMethod.POST)
	public String salvarSaida(Saida saida) {
		saida.setUsuario(usuario);
		saida.setMes(mes);
		saidaRepository.save(saida);
		return "redirect:/home/saidas/?mes=" + mes.getId();
	}
	
	@GetMapping("home/listarSaida")
	@ResponseBody
	public Optional<Saida> buscarSaida(long id) {

		return saidaRepository.findById(id);

	}

	

	@GetMapping("home/deletarSaida/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("redirect:/home/saidas/?mes=" + mes.getId());
		Optional<Saida> saida = saidaRepository.findById(id);
		saidaRepository.delete(saida.get());

		return mv;
	}
	
}
