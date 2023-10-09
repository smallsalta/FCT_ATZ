package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.EstadoDAO;
import com.atz.persistencia.TEstado;

@Service
public class EstadosService 
{
	@Autowired
	private EstadoDAO edao;
		
	@Transactional(readOnly=true)
	public List<TEstado> getTodos()
	{
		return this.edao.getAll();
	}
	
	@Transactional(readOnly=true)
	public TEstado getUno(int oid)
	{
		return this.edao.get(oid);
	}
}
