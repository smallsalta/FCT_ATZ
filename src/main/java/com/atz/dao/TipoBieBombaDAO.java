package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TTipoBie;
import com.atz.persistencia.TTipoBieBomba;

@Repository
public class TipoBieBombaDAO {

	@Autowired	
	private SessionFactory sessionFactory;
	
	public TTipoBieBomba get(Integer oid) {
		return (TTipoBieBomba) this.sessionFactory.getCurrentSession().get(TTipoBieBomba.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TTipoBieBomba> readAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from TTipoBieBomba").list();
	}
}
