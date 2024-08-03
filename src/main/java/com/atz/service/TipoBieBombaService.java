package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.TipoBieBombaDAO;
import com.atz.persistencia.TTipoBieBomba;

@Service
public class TipoBieBombaService {
	
	@Autowired
	private TipoBieBombaDAO dao;
	
	@Transactional(readOnly = true)
	public List<TTipoBieBomba> leerTodos() {
		return this.dao.readAll();
	}

}
