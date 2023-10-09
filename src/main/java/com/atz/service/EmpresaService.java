package com.atz.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.comparator.TEmpresaComp;
import com.atz.dao.EmpresaDAO;
import com.atz.persistencia.TEmpresa;

@Service
public class EmpresaService 
{
	@Autowired
	private EmpresaDAO edao;
	
	@Transactional(readOnly=true)
	public List<TEmpresa> leerTodas()
	{
		List<TEmpresa> l = this.edao.readAll();
		
		Collections.sort( l, new TEmpresaComp() );
		
		return l;
	}
}