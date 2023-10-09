package com.atz.main;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.persistencia.TContrato;
import com.atz.service.ContratoService;
import com.atz.service.PdfContratoService;

import net.sf.jasperreports.engine.JRException;

public class PruebaContrato1 
{
	public static void main(String[] args) 
	throws IllegalAccessException, InvocationTargetException, FileNotFoundException, JRException 
	{
	    ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
	    ContratoService serv	= ctx.getBean( ContratoService.class );
	    PdfContratoService pdf	= ctx.getBean( PdfContratoService.class );
	    TContrato contrato		= serv.leer(2841);
	    
	    pdf.crear(contrato);
	    
	    System.out.println(1);
	}
}