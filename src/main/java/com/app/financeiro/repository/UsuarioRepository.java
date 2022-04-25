package com.app.financeiro.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.financeiro.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Optional<Usuario> findById(long id);
	
	@Query("from Usuario where email=?1")
	public List<Usuario> buscarUsuarioEmail(String email);
}
