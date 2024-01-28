package com.atz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.fb.PicadasFb;
import com.atz.persistencia.TPicada;

@Repository
public class PicadaDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
	public void create(TPicada t)
	{
		this.sessionFactory.getCurrentSession().save(t);
	}
	
	public boolean existePicada(int oid, Date fecha)
	{
		String hql	= "FROM TPicada tp WHERE tp.TUsuario.oid = :oid AND tp.fecha = :fecha";
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 		= qry.setParameter("oid", oid);
		qry 		= qry.setParameter("fecha", fecha);
		
		return qry.list().size() != 0;
	}
	
	public List<TPicada> read(PicadasFb fb)
	{
		String hql	= "FROM TPicada tp WHERE tp.TUsuario.oid = :oid AND tp.fecha BETWEEN :fini AND :ffin ORDER BY tp.fecha DESC";
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 		= qry.setParameter( "oid", fb.getOidusuario() );
		qry 		= qry.setParameter( "fini", fb.getFini() );
		qry 		= qry.setParameter( "ffin", fb.getFfin() );
		
		return qry.list();
	}
	
	public TPicada read(int oid)
	{
		return this.sessionFactory.getCurrentSession().get( TPicada.class, oid );
	}
	
	public void delete(PicadasFb fb)
	{
		Session ses = this.sessionFactory.getCurrentSession();
		TPicada p	= ses.get( TPicada.class, fb.getOid() );
				
		ses.delete(p);
	}
}