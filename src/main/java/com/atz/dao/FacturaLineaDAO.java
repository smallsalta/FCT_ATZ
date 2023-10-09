package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TFacturaLinea;

@Repository
public class FacturaLineaDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TFacturaLinea> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TFacturaLinea").list();
    }    
    
	public TFacturaLinea get(int oid)
    {
    	return (TFacturaLinea) this.sessionFactory.getCurrentSession().get( TFacturaLinea.class , oid );
    }
	
	public TFacturaLinea create(TFacturaLinea tlc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tlc);
		
		return tlc;
	}
	
	public void delete(TFacturaLinea tlc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().delete(tlc);
	}
}