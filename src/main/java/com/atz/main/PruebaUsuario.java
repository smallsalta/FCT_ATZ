package com.atz.main;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.fb.UsuarioFb;
import com.atz.service.UsuarioService;

public class PruebaUsuario 
{
	public static void main(String[] args) 
	throws IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException 
	{
		Logger log				= LogManager.getLogger( PruebaUsuario.class );
	    ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
	    UsuarioService service	= ctx.getBean( UsuarioService.class );
	    UsuarioFb fb 			= new UsuarioFb();
	    
	    fb.setUsername("costalero");
	    fb.setPassword("taranto");
	    
	    log.info( service.getUno(fb) );
	}
}