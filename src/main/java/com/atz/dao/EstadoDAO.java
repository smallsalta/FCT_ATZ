package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TEstado;

@Repository
public class EstadoDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TEstado> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TEstado").list();
    }    
    
	public TEstado get(int oid)
    {
    	return (TEstado) this.sessionFactory.getCurrentSession().get( TEstado.class , oid );
    }
}