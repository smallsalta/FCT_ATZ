package com.atz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteCambios;

@Repository
public class ParteCambiosDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TParteCambios> readAll()
	{
		List<TParteCambios> l	=	this.sessionFactory.getCurrentSession().
									createQuery("FROM TParteCambios t ORDER BY fecha DESC").
									list();
		
		l.forEach( e -> Hibernate.initialize( e.getTUsuario() ) );
		
		return l;
	}  
	
	@SuppressWarnings("unchecked")
	public List<TParteCambios> readAll(int oidParte)
	{
		List<TParteCambios> l	=	this.sessionFactory.getCurrentSession().
									createQuery("FROM TParteCambios t WHERE t.TParte.oid = :oid ORDER BY fecha DESC").
									setParameter("oid", oidParte).
									list();
		
		l.forEach( e -> Hibernate.initialize( e.getTUsuario() ) );
		
		return l;	
	}  
	
	public TParteCambios create(TParte t) 
	{
		TParteCambios tpc = new TParteCambios();
		
		tpc.setTParte(t);
		tpc.setTUsuario( t.getTUsuario() );
		tpc.setFecha( new Date() );
		
		this.sessionFactory.getCurrentSession().save(tpc);
		
		return tpc;
	}
}
