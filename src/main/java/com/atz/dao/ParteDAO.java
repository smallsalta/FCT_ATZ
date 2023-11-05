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

import com.atz.persistencia.TParte;

@Repository
public class ParteDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TParte> readAll()
	{
		return this.sessionFactory.getCurrentSession().createQuery("from TParte").list();
	}    
	
	public List<TParte> readPartes(Date fini, Date ffin, Integer oidusuario, Integer oidcliente, Integer numero, Integer oidpartetipo, Integer oidestadoparte)
    {
		String hql		= "FROM TParte AS f INNER JOIN FETCH f.TCliente WHERE fecha BETWEEN :fini AND :ffin";
		List<TParte> lp	= null; 	
		
		if( oidusuario != null && oidusuario != -1 )
		{
			hql		+= " AND f.TUsuario.oid = :oidusuario";
		}
		
		if( oidcliente != null && oidcliente != -1 )
		{
			hql		+= " AND f.TCliente.oid = :oidcliente";
		}
		
		if( numero != null )
		{
			hql		+= " AND f.numero = :numero";
		}
		
		if( oidpartetipo != null ) 
		{
			hql 	+= " AND f.TParteTipo.oid = :oidpartetipo";
		}
		
		if( oidcliente != null && oidestadoparte != -1 ) 
		{
			hql 	+= " AND f.estado.oid = :oidestadoparte";
		}
		
		hql			+= " ORDER BY numero DESC";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 		= qry.setParameter("fini", fini);
		qry 		= qry.setParameter("ffin", ffin);
				
		if( oidusuario != null && oidusuario != -1 )
		{
			qry 	= qry.setParameter( "oidusuario", oidusuario );
		}
		
		if( oidcliente != null &&  oidcliente != -1 )
		{
			qry 	= qry.setParameter( "oidcliente", oidcliente );
		}
		
		if( numero != null )
		{
			qry 	= qry.setParameter( "numero", numero );
		}
		
		if( oidpartetipo != null ) 
		{
			qry 	= qry.setParameter( "oidpartetipo", oidpartetipo);
		}
		
		if( oidestadoparte != -1 ) 
		{
			qry 	= qry.setParameter( "oidestadoparte", oidestadoparte);
		}
		
		lp = qry.list();
		
		lp.forEach( t -> Hibernate.initialize( t.getEstado() ) );
		
		
		return lp;
	}
	
	public List<TParte> readPartes(Date fini, Date ffin)
    {
		String hql		= "FROM TParte AS f INNER JOIN FETCH f.TCliente WHERE fecha BETWEEN :fini AND :ffin";
		hql				+= " ORDER BY fecha desc, numero desc";
		
		Query qry 		= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 			= qry.setParameter("fini", fini);
		qry 			= qry.setParameter("ffin", ffin);
				
		List<TParte> l 	= qry.list();
		
		l.forEach( p -> Hibernate.initialize( p.getTParteLineas() ) );
		
		return l;
	}
	
	public Integer readSiguienteNumero()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("select COALESCE( max(numero), 0 ) "); 
		sb.append("from t_parte p ");
		
		Query q		= this.sessionFactory.getCurrentSession().createSQLQuery( sb.toString() );
		Integer act	= (Integer) q.uniqueResult();
		
		return act + 1;
	}
	
	public TParte create(TParte tc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tc);
		
		return tc;
	}
	
	public TParte read(int oid)
    {
		TParte f = (TParte) this.sessionFactory.getCurrentSession().get( TParte.class , oid );
		
		Hibernate.initialize( f.getTCliente() );
		Hibernate.initialize(f.getTParteLineas());
		Hibernate.initialize( f.getEstado() );
		
		f.getTParteLineas().forEach(x -> {
				Hibernate.initialize(x.getTipo());
				Hibernate.initialize(x.getTipoBie());
			});
		
		return f;
    }
	
	public void update(TParte tp) 
	throws IllegalAccessException, InvocationTargetException{
		this.sessionFactory.getCurrentSession().update(tp);
	}

	public void delete(int u) {
		Session cs	= this.sessionFactory.getCurrentSession();
		TParte p = (TParte) cs.get(TParte.class, u);
		
		cs.delete(p);
	}
}
