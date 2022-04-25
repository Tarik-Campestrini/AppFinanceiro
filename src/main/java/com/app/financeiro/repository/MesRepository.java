package com.app.financeiro.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.app.financeiro.models.Mes;


public interface MesRepository extends CrudRepository<Mes, String> {

	@Query("from Mes where mes=?1")
	public List<Mes> buscarMes(Long mes_id);

	@Query("from Mes where id=?1")
	public Mes buscarMesSelecionado(long id);

	@Component
	public class DataInicializr implements ApplicationListener<ContextRefreshedEvent> {

		@Autowired
		MesRepository mesRepository;
		
		@Override
		public void onApplicationEvent(ContextRefreshedEvent arg0) {
			//seta o mes no inicio da aplicacao
			
			Mes mes1 = new Mes();			
			mes1.setMes("Janeiro");
			mesRepository.save(mes1);
			
			
			Mes mes2 = new Mes();	
			mes2.setMes("fevereiro");
			mesRepository.save(mes2);
			
			Mes mes3 = new Mes();	
			mes3.setMes("Março");
			mesRepository.save(mes3);
			
			Mes mes4 = new Mes();	
			mes4.setMes("Abril");
			mesRepository.save(mes4);
			
			Mes mes5 = new Mes();	
			mes5.setMes("Maio");
			mesRepository.save(mes5);
			
			Mes mes6 = new Mes();	
			mes6.setMes("Junho");
			mesRepository.save(mes6);
			
			Mes mes7 = new Mes();	
			mes7.setMes("Julho");
			mesRepository.save(mes7);
			
			Mes mes8 = new Mes();	
			mes8.setMes("Agosto");
			mesRepository.save(mes8);
			
			Mes mes9 = new Mes();	
			mes9.setMes("Setembro");
			mesRepository.save(mes9);
			
			Mes mes10 = new Mes();	
			mes10.setMes("Outubro");
			mesRepository.save(mes10);
			
			Mes mes11 = new Mes();	
			mes11.setMes("Novembro");
			mesRepository.save(mes11);
			
			Mes mes12 = new Mes();	
			mes12.setMes("Dezembro");
			mesRepository.save(mes12);
			
			
			
						
		}
	
}
}
