package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TParteModalidad;

@Repository
public class ParteModalidadDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public TParteModalidad get(Integer oid) {
		return (TParteModalidad) this.sessionFactory.getCurrentSession().get(TParteModalidad.class, oid);
	}
	
	@SuppressWarnings("unchecked")
	public List<TParteModalidad> getAll() 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from TParteModalidad").list();
	}
}
