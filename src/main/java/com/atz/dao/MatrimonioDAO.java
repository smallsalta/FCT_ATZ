package com.atz.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atz.persistencia.TMatrimonio;

@Repository
public class MatrimonioDAO 
{
	@Autowired
    private SessionFactory sessionFactory;
	
	/**
	 * No se puede crear un matrimonio si no hay {parte, factura, contrato}
	 * No se puede borrar un matrimonio si no hay otro para reemplazarlo
	 * No se puede crear un matrimonio si los datos no son válidos
	 * 
	 * Estas validaciones se pueden hacer con JS
	 * 
	 * @param tm	Matrimonio candidato
	 * @return		Matrimonio final
	 * 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public TMatrimonio create(TMatrimonio tm) 
	throws IllegalAccessException, InvocationTargetException
	{
		Session ses = this.sessionFactory.getCurrentSession();
		
		if( tm.getOid() != 0 )
		{
			this.delete( tm.getOid() );
		}
		
		ses.save(tm);
		
		return tm;
	}
	
	public void delete(int oid) 
	throws IllegalAccessException, InvocationTargetException
	{
		Session ses = this.sessionFactory.getCurrentSession();
		
		ses.delete
		( 
			ses.load( TMatrimonio.class, oid ) 
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<TMatrimonio> listFromPartes(List<Integer> lp) 
	throws IllegalAccessException, InvocationTargetException
	{
		String hql	= "from TMatrimonio m where m.parte in (:partes)";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
	
		qry 		= qry.setParameterList("partes", lp);
		
		return qry.list();
	}
	
	/**
	 * Sólo se puede hacer un matrimonio si los datos de parte, factura y contrato son correctos.
	 * @param tm	Posible matrimonio a persistir
	 * @return		TRUE si los datos {parte, factura, contrato} son correctos
	 */
	public boolean esPosible1(TMatrimonio tm)
	{
		String sql	= "select count(*) as cont "
					+ "from t_parte tp, t_factura tf, t_contrato tc "
					+ "where tp.numero = :tp "
					+ "and tf.numero = :tf "
					+ "and tc.numero = :tc";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		qry 		= qry.setParameter( "tp", tm.getParte() );
		qry 		= qry.setParameter( "tf", tm.getFactura() );
		qry 		= qry.setParameter( "tc", tm.getContrato() );
		
		return ( (BigInteger) qry.uniqueResult() ).intValue() > 0;
		
	}
	
	/**
	 * Sólo se puede hacer un matrimonio si los datos de {factura, contrato} no se han usado antes
	 * @param tm	Posible matrimonio a persistir
	 * @return		TRUE si {factura, contrato} no existe en la base de datos
	 */
	public boolean esPosible2(TMatrimonio tm)
	{
		String sql	= "select count(*) as cont "
					+ "from t_matrimonio tm "
					+ "where tm.factura = :tf "
					+ "and tm.contrato = :tc ";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		qry 		= qry.setParameter( "tf", tm.getFactura() );
		qry 		= qry.setParameter( "tc", tm.getContrato() );
		
		return ( (BigInteger) qry.uniqueResult() ).intValue() == 0;
		
	}
	
	public TMatrimonio getByOid(int nparte)
	{
		String hql	= "from TMatrimonio tm where tm.parte = :tp ";
		
		Query qry 	= this.sessionFactory.getCurrentSession().createQuery(hql);
		qry 		= qry.setParameter( "tp", nparte );
		Object res	= qry.uniqueResult();
		
		return (TMatrimonio) res; 
		
	}
}
