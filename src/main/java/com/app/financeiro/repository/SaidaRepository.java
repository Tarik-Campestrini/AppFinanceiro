package com.app.financeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.financeiro.models.Saida;
import com.app.financeiro.models.Usuario;

public interface SaidaRepository extends CrudRepository<Saida, String> {

Optional<Saida> findById(long id);
	
	@Query("from Saida where usuario_id=?1 and mes_id=?2 order by data")
	public List<Saida> buscarSaidaIdUsuario(long usuario_id, long mes_id);
	
	Iterable<Saida> findByUsuario(Optional<Usuario> usuario);		

	void deleteById(long id);

}
