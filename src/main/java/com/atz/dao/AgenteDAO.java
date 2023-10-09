package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TAgente;

@Repository
public class AgenteDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TAgente> getAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TAgente order by descr").list();
    }    
    
	public TAgente get(int oid)
    {
    	return (TAgente) this.sessionFactory.getCurrentSession().get( TAgente.class , oid );
    }
	
	@SuppressWarnings("unchecked")
	public List<TAgente> getByDesc(String descr) {
		String hql = "from TAgente where descr = '" + descr + "'";
		
		return sessionFactory.getCurrentSession().createQuery(hql).list(); 
	}
}