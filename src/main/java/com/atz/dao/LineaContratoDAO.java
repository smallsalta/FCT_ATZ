package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TLineaContrato;

@Repository
public class LineaContratoDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TLineaContrato> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TLineaContrato").list();
    }    
    
	public TLineaContrato get(int oid)
    {
    	return (TLineaContrato) this.sessionFactory.getCurrentSession().get( TLineaContrato.class , oid );
    }
	
	public TLineaContrato create(TLineaContrato tlc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tlc);
		
		return tlc;
	}
	
	public void delete(TLineaContrato tlc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().delete(tlc);
	}
}