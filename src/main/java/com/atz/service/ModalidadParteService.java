package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.ParteModalidadDAO;
import com.atz.persistencia.TParteModalidad;

@Service
public class ModalidadParteService 
{

	@Autowired
	private ParteModalidadDAO dao;
	
	@Transactional(readOnly = true)
	public List<TParteModalidad> leerTodos() 
	{
		List<TParteModalidad> l = this.dao.getAll();
		
		return l;
	}
	
	@Transactional(readOnly = true)
	public TParteModalidad leerUno(Integer oid) 
	{
		return this.dao.get(oid);
	}
}
