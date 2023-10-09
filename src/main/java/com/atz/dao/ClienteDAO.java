package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TCliente;

@Repository
public class ClienteDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TCliente> readAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TCliente").list();
    }    
    
	public TCliente read(int oid)
    {
    	return (TCliente) this.sessionFactory.getCurrentSession().get( TCliente.class , oid );
    }
	
	public TCliente read(String nomApe)
    {
		String hql = "from TCliente where concat(nombre, ' ', apellidos) = '" + nomApe + "'";
		
		return (TCliente) this.sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
    }
	
	@SuppressWarnings("unchecked")
	public List<TCliente> getFromUser(int u)
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TCliente where TUsuario.oid = " + u).list();
    }
	
	public TCliente create(TCliente tc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tc);
		
		return tc;
	}
	
	public void update(TCliente tc)
	{
		this.sessionFactory.getCurrentSession().update(tc);
	}
	
	public void delete(int u)
	{
		Session cs	= this.sessionFactory.getCurrentSession();
		TCliente c	= (TCliente) cs.get( TCliente.class, u );
		
		cs.delete(c);
	}	
}