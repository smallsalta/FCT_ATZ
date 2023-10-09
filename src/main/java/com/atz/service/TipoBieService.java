package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.TipoBieDAO;
import com.atz.persistencia.TTipoBie;

@Service
public class TipoBieService {
	
	@Autowired
	private TipoBieDAO dao;
	
	@Transactional(readOnly = true)
	public List<TTipoBie> leerTodos() {
		return this.dao.readAll();
	}

}
