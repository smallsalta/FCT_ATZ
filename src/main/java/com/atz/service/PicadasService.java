package com.atz.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.PicadaDAO;
import com.atz.fb.PicadasFb;
import com.atz.persistencia.TPicada;
import com.atz.persistencia.TUsuario;

@Service
public class PicadasService 
{
	@Autowired
	private PicadaDAO dao;
	
	/**
	 * Crea una picada para el usuario y la fecha/hora indicada.
	 * 
	 * Sólo se crea una picada si:
	 * + No existe otra picada previa en la misma fecha
	 * + La hora de inicio es menor que la de fin.
	 * 
	 * @param u		Usuario
	 * @param fb	{ fecha, hora inicio, hora fin }
	 * 
	 * @throws ParseException
	 */
	@Transactional
	public void altaPicada(TUsuario u, PicadasFb fb) 
	throws ParseException
	{
		DateFormat dfh	= new SimpleDateFormat("HH:mm");
		
		Date f 			= fb.getFecha();
		Date hi			= dfh.parse( fb.getHini() );
		Date hf			= dfh.parse( fb.getHfin() );
		
		if( hi.before(hf) )
		{
			if( !this.dao.existePicada( u.getOid(), f ) )
			{
				TPicada tp = new TPicada();
				
				tp.setFecha(f);
				tp.setHini(hi);
				tp.setHfin(hf);
				tp.setTUsuario(u);
				tp.setInfo( fb.getInfo() );
				
				this.dao.create(tp);
			}
		}
	}
	
	@Transactional
	public List<TPicada> leerPicadas(PicadasFb fb) 
	{
		return this.dao.read(fb);
	}
	
	@Transactional
	public TPicada leerPicada(PicadasFb fb) 
	{
		return this.dao.read( fb.getOid() );
	}
	
	@Transactional
	public void borrarPicadas(PicadasFb fb) 
	{
		this.dao.delete(fb);
	}
}
