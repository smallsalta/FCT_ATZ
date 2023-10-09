package com.atz.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.UsuarioDAO;
import com.atz.fb.UsuarioFb;
import com.atz.persistencia.TUsuario;

@Service
public class UsuarioService 
{
	@Autowired
	private UsuarioDAO udao;
		
	@Transactional(readOnly=true)
	public List<TUsuario> getTodos()
	{
		return this.udao.getAll();
	}
	
	@Transactional(readOnly=true)
	public TUsuario getUno(int oid)
	{
		return this.udao.get(oid);
	}
	
	@Transactional(readOnly=true)
	public TUsuario getUno(UsuarioFb fb) 
	throws NoSuchAlgorithmException
	{
		String username	= fb.getUsername().toLowerCase();
		String password	= fb.getPassword().toLowerCase();
		String md5		= md5(password);
		
		TUsuario u 		= this.udao.get(username, md5);
		
		if( u != null )
		{
			Hibernate.initialize( u.getTRol() );
		}
		
		return u;
	}
	
//	@Transactional(readOnly=true)
//	public List<TCliente> getMisClientes(int u)
//	{
//		return this.cdao.getFromUser(u);
//	}
	
	/**
	 * https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
	 * @param md5	Password
	 * @return		MD5(password)
	 */
	public String md5(String md5) 
	{
		StringBuffer sb = new StringBuffer();
		
		try 
		{
			MessageDigest md	= MessageDigest.getInstance("MD5");
			byte[] array		= md.digest(md5.getBytes());
			
			for (int i = 0; i<array.length; ++i) 
			{
				sb.append( Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3) );
			}
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			;
		}
		
		return sb.toString();
	}
}
