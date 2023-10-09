package com.atz.main;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.persistencia.TContrato;
import com.atz.service.ContratoService;
import com.atz.service.PdfContratoService;
import com.atz.service.SendMailService;

import net.sf.jasperreports.engine.JRException;

public class PruebaCorreo2 
{
	public static void main(String[] args) 
	throws AddressException, MessagingException, FileNotFoundException, JRException 
	{
		ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
		
		ContratoService serv	= ctx.getBean( ContratoService.class );
		SendMailService sm		= ctx.getBean( SendMailService.class );
		PdfContratoService pdf	= ctx.getBean( PdfContratoService.class );
	    TContrato contrato		= serv.leer(2841);  
	    
		sm.enviarConCCyCuadrante( contrato.getTCliente(), pdf.crear(contrato) );
		
		System.out.println(1);
	}
}
