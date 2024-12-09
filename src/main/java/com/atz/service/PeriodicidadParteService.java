package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.PartePeriodicidadDAO;
import com.atz.persistencia.TPartePeriodicidad;

@Service
public class PeriodicidadParteService {

	@Autowired
	private PartePeriodicidadDAO dao;
	
	@Transactional(readOnly = true)
	public List<TPartePeriodicidad> leerTodos() 
	{
		List<TPartePeriodicidad> l = this.dao.getAll();
		
		return l;
	}
	
	@Transactional(readOnly = true)
	public TPartePeriodicidad leerUno(Integer oid) 
	{
		return this.dao.get(oid);
	}
}
