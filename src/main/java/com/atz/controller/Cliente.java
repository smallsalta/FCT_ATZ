package com.atz.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.fb.ClientesFb;
import com.atz.persistencia.TCliente;
import com.atz.service.ClienteService;

@Controller
public class Cliente 
{
	@Autowired
	private ClienteService cservice;
	
	@RequestMapping("cliente_buscar.do")
	public ModelAndView buscar(HttpSession s) 
	{
		ModelMap m 	= new ModelMap();
		
		m.put( "clientes", this.cservice.leerTodos() );
		
		return new ModelAndView("cliente_buscar", m);
	}
	
	@RequestMapping("cliente_crear.do")
	public ModelAndView crear()  
	{
		return new ModelAndView("cliente_crear");
	}
	
	@RequestMapping("cliente_buscar_cargar_clientes.do")
	public ModelAndView cargaClientes(HttpSession sesion) 
	{
		List<TCliente> crudo 	= this.cservice.leerTodos();
		List<String> cocinado	= crudo.stream().map( t -> t.getNombre() + " " + t.getApellidos() ).collect( Collectors.toList() ) ;
		ModelMap datos			= new ModelMap();
		
		Collections.sort(cocinado);
		
		datos.put("clientes", cocinado);
		
		return new ModelAndView( "cliente_buscar_cargar_clientes", datos );
	}
	
	@RequestMapping("cliente_buscar_cargar.do")
	public ModelAndView burcarClientes(int oid) 
	{
		ModelMap datos		= new ModelMap();
		TCliente cliente 	= this.cservice.leer(oid);
		String buscar		= cliente.getNombre() + " " + cliente.getApellidos();
		
		datos.put("cadena", buscar);
		datos.put("cliente", cliente);
		
		return new ModelAndView("cliente_buscar_cargar", datos);
	}
	
	@RequestMapping("cliente_buscar_guardar.do")
	public ModelAndView buscarGuardar(ClientesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		ModelMap datos		= new ModelMap();
		TCliente cliente 	= this.cservice.actualizar(fb);
		
		datos.put("cliente", cliente);
		
		return new ModelAndView("cliente_buscar_cargar", datos);
	}
	
	@RequestMapping("cliente_crear_guardar.do")
	public ModelAndView crear(ClientesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TCliente c = this.cservice.crear(fb);
		
		return this.burcarClientes( c.getOid() );
	}
	
	@RequestMapping("cliente_borrar.do")
	public ModelAndView borrar(ClientesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.cservice.borrar( fb.getOid() );
		
		return this.buscar(s);
	}
}
