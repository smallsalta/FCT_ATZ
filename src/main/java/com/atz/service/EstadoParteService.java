package com.atz.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.comparator.TEstadoParteComp;
import com.atz.dao.EstadoParteDAO;
import com.atz.persistencia.TEstadoParte;

@Service
public class EstadoParteService {

	@Autowired
	private EstadoParteDAO epdao;
	
	@Transactional(readOnly = true)
	public List<TEstadoParte> leerTodos() 
	{
		List<TEstadoParte> l = this.epdao.readAll();
		
		Collections.sort( l, new TEstadoParteComp() );
		
		return l;
	}
}
