package com.atz.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.fb.ContratosFb;
import com.atz.persistencia.TContrato;
import com.atz.service.ContratoService;

public class PruebaContrato2 
{
	public static void main(String[] args) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Logger log				= LogManager.getLogger( PruebaContrato2.class );
	    ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
	    ContratoService serv	= ctx.getBean( ContratoService.class );
	    ContratosFb fb 			= new ContratosFb();
	    
	    fb.setDireccion("Calle");
	    fb.setFecha( new Date() );
	    fb.setNumero(356);
	    fb.setOidcliente(1);
	    
	    fb.setCantidadExt( new Double[] {1D, 1D} );
	    fb.setCapacidadExt( new Double[] {1D, 1D} );
	    fb.setFechaFabExt( new Date[] {new Date(), new Date()} );
	    fb.setNumeroPlacaExt( new String[] {"1", "1"} );
	    fb.setPrecioExt( new Double[] {1D, 1D} );
	    fb.setAgentesExt( new Integer[] {1, 1} );
	    fb.setPruebasExt( new Integer[] {1, 1} );
	    
	    TContrato tc = serv.crear(fb);
	    
	    fb.setAnexo("Anexo");
	    fb.setPruebasExt( new Integer[] {2, 2} );
	    fb.setOid( tc.getOid() );
	    
	    serv.actualizar(fb);
	    
	    log.info( serv.leer( tc.getOid() ) );
	}
}