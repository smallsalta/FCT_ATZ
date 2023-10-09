package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.TipoExtintorDAO;
import com.atz.persistencia.TTipoExtintor;

@Service
public class TipoExtintorService {

	@Autowired
	private TipoExtintorDAO tedao;
	
	@Transactional(readOnly = true)
	public List<TTipoExtintor> leerTodos() {
		return this.tedao.readAll();
	}
	
	@Transactional(readOnly = true)
	public TTipoExtintor leer(int oid) {
		return this.tedao.get(oid);
	}
	
	
}
