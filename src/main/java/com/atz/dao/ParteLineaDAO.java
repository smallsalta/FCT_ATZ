package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TParteLinea;

@Repository
public class ParteLineaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TParteLinea> readAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from TParteLinea").list();
	}
	
	public void delete(TParteLinea tpl) 
	throws IllegalAccessException, InvocationTargetException {
		this.sessionFactory.getCurrentSession().delete(tpl);
	}

}
