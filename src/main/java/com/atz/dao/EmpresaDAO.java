package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TEmpresa;

@Repository
public class EmpresaDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TEmpresa> readAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TEmpresa order by oid").list();
    }    
    
	public TEmpresa read(int oid)
    {
    	return (TEmpresa) this.sessionFactory.getCurrentSession().get( TEmpresa.class , oid );
    }
}