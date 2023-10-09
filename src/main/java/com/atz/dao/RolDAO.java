package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TRol;

@Repository
public class RolDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TRol> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TRol").list();
    }    
    
	public TRol get(int oid)
    {
    	return (TRol) this.sessionFactory.getCurrentSession().get( TRol.class , oid );
    }
}