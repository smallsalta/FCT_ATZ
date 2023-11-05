package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.MatrimonioDAO;
import com.atz.fb.ParteBuscarFb;
import com.atz.persistencia.TMatrimonio;
import com.atz.persistencia.TParte;

@Service
public class MatrimonioService 
{
	@Autowired
	private MatrimonioDAO mdao;
	
	@Transactional(readOnly=false)
	public TMatrimonio guardar(ParteBuscarFb fb, String numero2) 
	throws IllegalAccessException, InvocationTargetException
	{
		TMatrimonio tm = new TMatrimonio();
		
		tm.setOid( fb.getOidmatrimonio() );
		tm.setParte( fb.getNparte() );
		tm.setFactura( fb.getNfactura() );
		tm.setContrato( fb.getNcontrato() );
		tm.setNumero2(numero2);
		
		tm = this.mdao.create(tm);
		
		return tm;
	}
	
	@Transactional(readOnly=true)
	public List<TMatrimonio> leer(List<TParte> lp) 
	throws IllegalAccessException, InvocationTargetException
	{
		return this.mdao.
				listFromPartes
				(
					lp.stream().map( t -> t.getNumero() ).collect( Collectors.toList() )
				);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void borrar(ParteBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.mdao.delete(fb.getOidmatrimonio() ); 
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TMatrimonio obtenerPorOid(int oid) 
	throws IllegalAccessException, InvocationTargetException
	{
		TMatrimonio tm = this.mdao.getByOid(oid);
		
		return tm == null ? new TMatrimonio() : tm;
	}
}
