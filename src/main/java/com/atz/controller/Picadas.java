package com.atz.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.fb.PicadasFb;
import com.atz.persistencia.TUsuario;
import com.atz.service.PicadasService;
import com.atz.service.UsuarioService;

@Controller
public class Picadas 
{
	@Autowired
	private PicadasService ps;
	
	@Autowired
	private UsuarioService us;
	
	private Logger log = LogManager.getLogger( Contrato.class );
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
	} 
	
	@RequestMapping("picadas_alta.do")
	public ModelAndView alta() 
	{
		ModelMap m = new ModelMap();
		
		return new ModelAndView("picadas_alta", m);
	}
	
	@RequestMapping("picadas_guardar.do")
	public ModelAndView guardar(PicadasFb fb, HttpSession ses) 
	throws ParseException 
	{
		TUsuario tus = (TUsuario) ses.getAttribute("usuario");
		
		this.log.info(fb);
		
		this.ps.altaPicada( tus, fb );
		
		return this.obtenerUltimasPicadas(ses, fb);
	}
	
	@RequestMapping("picadas_buscar.do")
	public ModelAndView buscar(HttpSession ses) 
	{
		ModelMap m			= new ModelMap();
		TUsuario tus		= (TUsuario) ses.getAttribute("usuario");
		List<TUsuario> lus	= ( tus.getOid() == 1 ) ? this.us.getTodos() : Arrays.asList(tus);
		
		m.put( "usuarios", lus );
		m.put( "fini", new Date() );
		m.put( "ffin", new Date() );
		
		return new ModelAndView("picadas_buscar", m);
	}
	
	@RequestMapping("picadas_buscar_listado.do")
	public ModelAndView listadoResultado(PicadasFb fb) 
	{
		ModelMap m = new ModelMap();
		
		m.put( "picadas", this.ps.leerPicadas(fb) );
		
		return new ModelAndView("picadas_listado_resultado", m);
	}
	
	@RequestMapping("picadas_borrar.do")
	public ModelAndView borrar(PicadasFb fb, HttpSession ses) 
	{
		this.ps.borrarPicadas(fb);
		
		return this.obtenerUltimasPicadas(ses, fb);
	}
	
	private ModelAndView obtenerUltimasPicadas(HttpSession ses, PicadasFb fb)
	{
		Calendar cal	= Calendar.getInstance();
		TUsuario tus	= (TUsuario) ses.getAttribute("usuario");
		
		fb.setOidusuario( tus.getOid() );
		fb.setFfin( cal.getTime() );
		
		cal.add( Calendar.DAY_OF_YEAR , -30 );
		fb.setFini( cal.getTime() );
		
		return listadoResultado(fb);
	}
}