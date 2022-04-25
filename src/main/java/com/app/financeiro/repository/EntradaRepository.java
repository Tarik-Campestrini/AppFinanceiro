package com.app.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.financeiro.models.Entrada;
import com.app.financeiro.models.Usuario;

public interface EntradaRepository extends CrudRepository<Entrada, String> {	
	
	Optional<Entrada> findById(long id);
	
	@Query("from Entrada where usuario_id=?1 and mes_id=?2 order by data")
	public List<Entrada> buscarEntradaIdUsuario(long usuario_id, long mes_id);
	
	Iterable<Entrada> findByUsuario(Optional<Usuario> usuario);		

	void deleteById(long id);
}
