package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TPartePeriodicidad;

@Repository
public class PartePeriodicidadDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public TPartePeriodicidad get(Integer oid) 
	{
		return (TPartePeriodicidad) this.sessionFactory.getCurrentSession().get(TPartePeriodicidad.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TPartePeriodicidad> getAll() 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from TPartePeriodicidad").list();
	}
}
