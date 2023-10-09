package com.atz.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.atz.pdf.ExtintorFb;
import com.atz.pdf.ExtintorFbArray;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class PruebaCertificado 
{
	public static void main(String[] args) 
	throws FileNotFoundException, JRException 
	{
		List<ExtintorFb> lext		= new LinkedList<>();
		
		for(int i=0; i<5; i++)
		{
			lext.add( new ExtintorFb() );
		}
		
		Map<String, Object> param	= new HashMap<>();
		ExtintorFbArray efa			= new ExtintorFbArray(lext);
		PruebaCertificado p 		= new PruebaCertificado();		
		
		ClassLoader classLoader 	= p.getClass().getClassLoader();
		File file 					= new File( classLoader.getResource("atz.jasper").getFile() );
		FileInputStream is			= new FileInputStream(file);
		
		param.put("nombre", "1");
		param.put("direccionCli", "2");
		param.put("fecha", new Date());
		param.put("numero", "4");
		param.put("direccionCert", "5");
		param.put("extintores", efa);
		param.put("anexo", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper fringilla est, a convallis diam sodales sit amet. Duis cursus mollis massa, at consequat turpis elementum vel. Donec vitae enim purus. Donec laoreet a lacus ut efficitur. Phasellus lacinia accumsan pharetra. Fusce imperdiet euismod nibh, et vehicula mi condimentum ut. Curabitur at urna purus.");
		param.put("precio", 23.82);
		
		JasperPrint fis 			= JasperFillManager.fillReport( is, param, new JREmptyDataSource() );

		JasperExportManager.exportReportToPdfFile( fis, "C:\\Users\\jsilva\\Desktop\\f.pdf" );
		
		System.out.println(1);
	}
}
