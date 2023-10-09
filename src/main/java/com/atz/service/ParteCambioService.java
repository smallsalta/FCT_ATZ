package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.ParteCambiosDAO;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteCambios;

@Service
public class ParteCambioService 
{
	@Autowired
	private ParteCambiosDAO pdao;
	
	@Transactional(readOnly=true)
	public List<TParteCambios> leerTodos()
	{
		return this.pdao.readAll();
	}
	
	@Transactional(readOnly=true)
	public List<TParteCambios> leerTodos(int oid)
	{
		return this.pdao.readAll(oid);
	}
	
	@Transactional(readOnly=false)
	public TParteCambios crear(TParte t) 
	{
		return this.pdao.create(t);
	}	
}