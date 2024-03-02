package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TContrato;

@Repository
public class ContratoDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TContrato> readAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TContrato").list();
    }    
    
	public TContrato read(int oid)
    {
		TContrato tc = (TContrato) this.sessionFactory.getCurrentSession().get( TContrato.class , oid );
		
		Hibernate.initialize( tc.getTLineaContratos() );
		Hibernate.initialize( tc.getTCliente() );
		Hibernate.initialize( tc.getTPreguntasContratos() );

		return tc;
    }
	
	public TContrato readNumero(int num)
    {
		String hql	= "FROM TContrato WHERE numero = :num";
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry.setParameter("num", num);
		
		return (TContrato) qry.uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<TContrato> readContratosCliente(int oid)
    {
		return this.sessionFactory.getCurrentSession().createQuery( "from TContrato where TCliente.oid = " +  oid + " order by fecha desc").list();
    }
	
	@SuppressWarnings("unchecked")
	public List<TContrato> readContratosFechas(Date fini, Date ffin, Integer oid)
    {
		String hql	= "FROM TContrato k JOIN FETCH k.TCliente c ";
		hql			+= "WHERE fecha BETWEEN :fini AND :ffin ";
		Query qry 	= null;
		
		if( oid != null )
		{
			hql	+= "AND c.TUsuario.oid = :oid ";
		}
		
		hql	+= "ORDER BY fecha DESC ";
		
		qry	= this.sessionFactory.getCurrentSession().createQuery(hql);
		qry.setParameter("fini", fini).setParameter("ffin", ffin);			
		
		if( oid != null )
		{
			qry.setParameter("oid", oid);
		}
		
		return qry.list();
	}
	
	public TContrato create(TContrato tc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tc);
		
		return tc;
	}
	
	public void update(TContrato tc)
	{
		this.sessionFactory.getCurrentSession().update(tc);
	}
	
	public void delete(int u)
	{
		Session cs	= this.sessionFactory.getCurrentSession();
		TContrato c	= (TContrato) cs.get( TContrato.class, u );
		
		cs.delete(c);
	}
	
	public synchronized Integer readSiguienteNumero()
	{
		String sql 	= "select coalesce( max(numero), 0 ) from t_contrato";
//		String sql 	= "select numero from max_numero_contrato";
		Integer act	= (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		
		return act + 1;
	}
}