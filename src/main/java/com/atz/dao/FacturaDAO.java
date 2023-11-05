package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TFactura;

@Repository
public class FacturaDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TFactura> readAll()
    {
    	return this.sessionFactory.getCurrentSession().createQuery("from TFactura").list();
    }    
    
	public TFactura read(int oid)
    {
//		String sql 	= "from TFactura as f "
//				+ "inner join fetch f.TEmpresa "
//				+ "inner join fetch f.TCliente "
//				+ "inner join fetch f.TCliente2 "
//				+ "inner join fetch f.TFacturaLineas "
//				+ "where f.oid = :oid";
//		
//		Query hql 	= this.sessionFactory.getCurrentSession().createQuery(sql);
//		hql			= hql.setParameter("oid", oid);
//
//		return (TFactura) hql.uniqueResult();
		
		TFactura f = (TFactura) this.sessionFactory.getCurrentSession().get( TFactura.class , oid );
		
		Hibernate.initialize( f.getTCliente() );
		Hibernate.initialize( f.getTCliente2() );
		Hibernate.initialize( f.getTEmpresa() );
		Hibernate.initialize( f.getTFacturaLineas() );
		
		return f;
    }
	
	@SuppressWarnings("unchecked")
	public List<TFactura> readFacturasCliente(int fk)
    {
		String hql	= "from TFactura where fk_cliente = :fk order by fecha desc";
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry.setParameter("fk", fk);			
		
		return qry.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TFactura> readFacturasNumero2(List<String> n2)
    {
		String hql	= "from TFactura f where f.numero2 in ( :n2 )";
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry.setParameterList("n2", n2);			
		
		List<TFactura> lf 	= qry.list();
		lf 					= lf.stream().filter( t -> t.getTEstado() != null ).collect( Collectors.toList() );
		
		lf.forEach( t -> Hibernate.initialize( t.getTEstado() ) );
		
		return lf;
	}
	
	@SuppressWarnings("unchecked")
	public List<TFactura> readFacturasFechas(Date fini, Date ffin, Integer oidempresa, Integer oidcliente, Integer oid)
    {
		String hql	= "from TFactura as f "
					+ "inner join fetch f.TCliente "
					+ "inner join fetch f.TCliente2 "
					+ "where f.fecha between :fini and :ffin and f.TEmpresa.oid = :oidempresa";
		
		if( oidcliente != null && oidcliente > -1) {
			hql += " and f.TCliente.oid = :oidcliente";
		}
		
		if( oid != null )
		{
			hql	+= " and f.TCliente.TUsuario.oid = :oid";
		}
		
		
		hql			+= " order by numero desc";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 		= qry.setParameter("fini", fini);
		qry 		= qry.setParameter("ffin", ffin);
		qry 		= qry.setParameter("oidempresa", oidempresa);
		
		if( oidcliente != null && oidcliente > -1) {
			qry = qry.setParameter("oidcliente", oidcliente);
		}
		
		if( oid != null )
		{				
			qry = qry.setParameter("oid", oid);
		}
		
		return qry.list();
	}
	
	public TFactura create(TFactura tc) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.sessionFactory.getCurrentSession().save(tc);
		
		return tc;
	}
	
	public void update(TFactura tc)
	{
		this.sessionFactory.getCurrentSession().update(tc);
	}
	
	public void delete(int u)
	{
		Session cs	= this.sessionFactory.getCurrentSession();
		TFactura c	= (TFactura) cs.get( TFactura.class, u );
		
		cs.delete(c);
	}
	
	public Integer readSiguienteNumero(int oidempresa)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("select COALESCE( max(numero), 0 ) "); 
		sb.append("from t_factura f ");
		sb.append("where fk_empresa = :oid ");
		sb.append("and EXTRACT( YEAR FROM f.fecha ) = EXTRACT( YEAR FROM now() ) ");
		
		Query q		= this.sessionFactory.getCurrentSession().createSQLQuery( sb.toString() );
		q			= q.setParameter("oid", oidempresa);
		Integer act	= (Integer) q.uniqueResult();
		
		return act + 1;
	}
	
	public void updateFechaEnvio(Integer oid)
    {
		String sql	= "UPDATE t_factura SET envio=now() WHERE oid = :oid";
		Query qry 	= this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		qry.setParameter("oid", oid);
		qry.executeUpdate();
	}
	
	public List<TFactura> readFacturas(Date fini, Date ffin, Integer oidempresa, Integer oidcliente, Integer numero)
    {
		String hql	= "from TFactura as f inner join fetch f.TCliente2 where fecha between :fini and :ffin";
		
		if( oidempresa != -1 )
		{
			hql		+= " and f.TEmpresa.oid = :oidempresa";
		}
		
		if( oidcliente != -1 )
		{
			hql		+= " and f.TCliente.oid = :oidcliente";
		}
		
		if( numero != null )
		{
			hql		+= " and f.numero = :numero";
		}
		
		hql			+= " order by fecha desc, numero desc";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		
		qry 		= qry.setParameter("fini", fini);
		qry 		= qry.setParameter("ffin", ffin);
				
		if( oidempresa != -1 )
		{
			qry 	= qry.setParameter("oidempresa", oidempresa);
		}
		
		if( oidcliente != -1 )
		{
			qry 	= qry.setParameter("oidcliente", oidcliente);
		}
		
		if( numero != null )
		{
			qry 	= qry.setParameter("numero", numero);
		}
		
		return qry.list();
	}
}