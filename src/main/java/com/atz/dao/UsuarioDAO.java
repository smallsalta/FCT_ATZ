package com.atz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TUsuario;

@Repository
public class UsuarioDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public List<TUsuario> getAll()
    {
    	StringBuffer hql = new StringBuffer("from TUsuario");
    	
    	return this.sessionFactory.getCurrentSession().createQuery( hql.toString() ).list();
    }    
    
	public TUsuario get(int oid)
    {
    	return (TUsuario) this.sessionFactory.getCurrentSession().get( TUsuario.class , oid );
    }
	
	public TUsuario get(String username, String password)
    {
		StringBuffer hql = new StringBuffer("from TUsuario where username = '");
		hql.append(username);
		hql.append("' and password = '");
		hql.append(password);
		hql.append("'");
		
    	return (TUsuario) this.sessionFactory.getCurrentSession().createQuery( hql.toString() ).uniqueResult();
    }
}