package com.atz.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.fb.UsuarioFb;
import com.atz.persistencia.TUsuario;
import com.atz.service.UsuarioService;

@Controller
public class Inicio 
{
	@Autowired
	private UsuarioService servicio;
	
	@RequestMapping("inicio.do")
	public ModelAndView inicio(UsuarioFb fb, HttpSession sesion) 
	throws NoSuchAlgorithmException 
	{
		return new ModelAndView("inicio");
	}
	
	@RequestMapping("login.do")
	public ModelAndView login() 
	{
		return new ModelAndView("login");
	}
	
	@RequestMapping("j_spring_security_check")
	public ModelAndView login(UsuarioFb fb, HttpSession sesion) 
	throws NoSuchAlgorithmException
	{
		TUsuario u	= this.servicio.getUno(fb);
		String dst	= null;
		ModelMap m	= new ModelMap();
		
		if( u != null )
		{
			sesion.setAttribute("usuario", u);
			dst = "forward:inicio.do";
		}
		else
		{
			dst = "forward:login.do";
			m.put("error", "Usuario/contraseña incorrectos");
		}
		
		return new ModelAndView(dst, m);
	}
	
	@RequestMapping("j_spring_security_logout")
	public ModelAndView logout(HttpServletRequest req)
	{
		req.getSession().invalidate();
		
		return new ModelAndView("forward:login.do");
	}
}