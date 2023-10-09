package com.atz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.AgenteDAO;
import com.atz.persistencia.TAgente;

@Service
public class AgenteService {
	
	@Autowired
	private AgenteDAO dao;
	
	@Transactional(readOnly = true)
	public Integer getOidByDescr(String descr) {
		List<TAgente> a = dao.getByDesc(descr);
		if(!a.isEmpty()) {
			return a.get(0).getOid();
		} else {
			return 1;
		}
	}
	
	@Transactional(readOnly = true)
	public TAgente getByOid(Integer oid) {
		return this.dao.get(oid);
	}

}
