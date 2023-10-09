package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TTipoBie;

@Repository
public class TipoBieDAO {

	@Autowired	
	private SessionFactory sessionFactory;
	
	public TTipoBie get(Integer oid) {
		return (TTipoBie) this.sessionFactory.getCurrentSession().get(TTipoBie.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoBie> readAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoBie").list();
	}
}
