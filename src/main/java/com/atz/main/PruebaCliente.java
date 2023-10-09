package com.atz.main;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.fb.ClientesFb;
import com.atz.persistencia.TCliente;
import com.atz.service.ClienteService;

public class PruebaCliente 
{
	public static void main(String[] args) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Logger log				= LogManager.getLogger( PruebaCliente.class );
	    ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
	    ClienteService servicio = ctx.getBean( ClienteService.class );
	    ClientesFb fb 			= new ClientesFb();
	    
	    fb.setOidUsuario(1);
	    fb.setNombre("Pepe");
	    fb.setApellidos("Pepe");
	    fb.setDireccion("Pepe");
	    
	    TCliente tc = servicio.crear(fb);
	    
	    fb.setCp(41410);
	    fb.setOid( tc.getOid() );
	    
	    servicio.actualizar(fb);
	    
	    log.info( servicio.leer( tc.getOid() ) );
	}
}