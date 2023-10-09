package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TEstadoParte;

@Repository
public class EstadoParteDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public TEstadoParte get(Integer oid) {
		return (TEstadoParte) this.sessionFactory.getCurrentSession().get(TEstadoParte.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TEstadoParte> readAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from TEstadoParte").list(); 
	}
	
}
