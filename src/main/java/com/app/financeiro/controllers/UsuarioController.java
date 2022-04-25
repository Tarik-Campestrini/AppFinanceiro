package com.app.financeiro.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.financeiro.models.Usuario;
import com.app.financeiro.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	private Usuario usuario;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
	public ModelAndView cadastrarUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/formUsuario");
		
		return mv;

	}

	@GetMapping("/home/editUsuario")
	public ModelAndView editarUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/formEditUsuario");
		mv.addObject("usuario", usuario);
		return mv;

	}

	@GetMapping("home/listarUsuario")
	@ResponseBody
	public Optional<Usuario> buscarusuario(long id) {

		return usuarioRepository.findById(id);
	}

	@ResponseBody
	@RequestMapping(value = "home/detalheUsuario", method = RequestMethod.GET)
	public ModelAndView listarUsuario() {
		buscarUsuarioLogado();
		ModelAndView mv = new ModelAndView("usuario/detalheUsuario");
		mv.addObject("usuario", usuario);
		

		return mv;
	}

	public void buscarUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			usuario = usuarioRepository.buscarUsuarioEmail(email).get(0);
		}

	}

	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return cadastrarUsuario(usuario);

		} else {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuarioRepository.save(usuario);
			ModelAndView mv = new ModelAndView("redirect:/login");
			attributes.addFlashAttribute("menssagem", "Usuário salvo com sucesso!");
			return mv;
		}

	}
	
	@RequestMapping(value = "home/editarUsuario", method = RequestMethod.POST)
	public ModelAndView editarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return cadastrarUsuario(usuario);

		} else {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuarioRepository.save(usuario);
			ModelAndView mv = new ModelAndView("redirect:/home/detalheUsuario");
			attributes.addFlashAttribute("menssagem", "Usuário salvo com sucesso!");
			return mv;
		}

	}

	@GetMapping("home/deletarUsuario/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {

		ModelAndView mv = new ModelAndView("redirect:/");
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		usuarioRepository.delete(usuario.get());

		return mv;
	}

}
