package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TPrueba;

@Repository
public class PruebaDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TPrueba> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TPrueba order by descr").list();
    }    
    
	public TPrueba get(int oid)
    {
    	return (TPrueba) this.sessionFactory.getCurrentSession().get( TPrueba.class , oid );
    }
}