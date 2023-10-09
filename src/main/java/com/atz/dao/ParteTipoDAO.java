package com.atz.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TParteTipo;

@Repository
public class ParteTipoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public TParteTipo get(Integer oid) {
		return (TParteTipo) this.sessionFactory.getCurrentSession().get(TParteTipo.class, oid);
	}
}
