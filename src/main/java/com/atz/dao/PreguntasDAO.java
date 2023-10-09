package com.atz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TPreguntas;
import com.atz.persistencia.TPreguntasContrato;
import com.atz.persistencia.TPreguntasParte;
import com.atz.persistencia.TPreguntasRespuestas;

@Repository
public class PreguntasDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TPreguntas> getQuestions(Integer oid)
	{
		String hql 	= "from TPreguntas where oidagente = :oid";
		Query q 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		q.setParameter( "oid", oid );
		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TPreguntas> getQuestions()
	{
		String hql 	= "from TPreguntas";
		Query q 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TPreguntasRespuestas> getAnswers()
	{
		return this.sessionFactory.getCurrentSession().createQuery("from TPreguntasRespuestas").list();
	}
	
	public TPreguntas getQuestion(int oid)
	{
		return this.sessionFactory.getCurrentSession().get( TPreguntas.class, oid );
	}
	
	public TPreguntasRespuestas getAswer(int oid)
	{
		return this.sessionFactory.getCurrentSession().get( TPreguntasRespuestas.class, oid );
	}
	
	public void delete(TPreguntasContrato t)
	{
		this.sessionFactory.getCurrentSession().delete(t);
	}
	
	public void delete(TPreguntasParte p) {
		this.sessionFactory.getCurrentSession().delete(p);
	}
}