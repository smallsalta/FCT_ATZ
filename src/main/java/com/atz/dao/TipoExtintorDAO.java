package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TTipoExtintor;

@Repository
public class TipoExtintorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public TTipoExtintor get(Integer oid) {
		return (TTipoExtintor) this.sessionFactory.getCurrentSession().get(TTipoExtintor.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoExtintor> readAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoExtintor").list();
	}
}
