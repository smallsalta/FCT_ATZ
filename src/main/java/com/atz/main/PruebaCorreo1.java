package com.atz.main;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.persistencia.TCliente;
import com.atz.service.SendMailService;

public class PruebaCorreo1 
{
	public static void main(String[] args) 
	throws AddressException, MessagingException 
	{
		ApplicationContext ctx	= new ClassPathXmlApplicationContext("beans_*.xml");
		SendMailService sm		= ctx.getBean( SendMailService.class );
		TCliente c 				= new TCliente();
		File f 					= new File("C:\\Users\\jsilva\\Desktop\\f.png");
		
		c.setEmail("smallsalta@gmail.com");
		sm.enviarSinCC(c, f);
		
		System.out.println(1);
	}
}
