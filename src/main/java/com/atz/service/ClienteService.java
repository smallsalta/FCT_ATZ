package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.atz.comparator.TClienteComp;
import com.atz.dao.ClienteDAO;
import com.atz.dao.UsuarioDAO;
import com.atz.fb.ClientesFb;
import com.atz.persistencia.TCliente;

@Service
public class ClienteService 
{
	@Autowired
	private UsuarioDAO udao;
	
	@Autowired
	private ClienteDAO cdao;
	
	@Transactional(readOnly=true)
	public List<TCliente> leerTodos()
	{
		List<TCliente> l = this.cdao.readAll();
		
		Collections.sort(l, new TClienteComp());
		
		return l;
	}
	
	@Transactional(readOnly=true)
	public TCliente leer(int oid)
	{
		return this.cdao.read(oid);
	}
	
	@Transactional(readOnly=true)
	public TCliente leer(String nomApe)
	{
		return this.cdao.read(nomApe);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TCliente crear(ClientesFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TCliente tc	= new TCliente();
		
		this.copy(fb, tc);
		
		return this.cdao.create(tc);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void borrar(int u) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.cdao.delete(u);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TCliente actualizar(ClientesFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TCliente tc	= this.cdao.read( fb.getOid() );
		
		this.copy(fb, tc);
		
		this.cdao.update(tc);
		
		return tc;
	}
	
	private void copy(ClientesFb fb, TCliente tc)
	{
		tc.setTUsuario( this.udao.get( fb.getOidUsuario() ) );
		tc.setNombre( fb.getNombre() );
		tc.setApellidos( fb.getApellidos() );
		tc.setDni( fb.getDni() );
		tc.setDireccion( fb.getDireccion() );
		tc.setLocalidad( fb.getLocalidad() );
		tc.setProvincia( fb.getProvincia() );
		tc.setCp( fb.getCp() );
		tc.setTelefono1( fb.getTelefono1() );
		tc.setTelefono2( fb.getTelefono2() );
		tc.setObservaciones( fb.getObservaciones() );
		tc.setEmail( fb.getEmail() );
	}
}
