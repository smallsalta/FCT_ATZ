package com.atz.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atz.persistencia.TFactura;
import com.atz.service.FacturaService;
import com.atz.service.PdfFacturaService;

import net.sf.jasperreports.engine.JRException;

public class PruebaFactura 
{
	public static void main(String[] args) 
	throws FileNotFoundException, JRException, ParseException 
	{
		ApplicationContext ctx		= new ClassPathXmlApplicationContext("beans_*.xml");
		
		FacturaService fdao			= ctx.getBean( FacturaService.class );
		PdfFacturaService fserv		= ctx.getBean( PdfFacturaService.class );
		
		DateFormat fmt				= new SimpleDateFormat("dd/MM/yyyy");
		Date fini					= fmt.parse("16/08/2024");
		Date ffin					= fmt.parse("16/08/2024");
		List<TFactura> lfact		= fdao.leerFacturasFechas( fini, ffin, 7, null, null );

		fserv.setPdfFolder( new File("C:\\Users\\jsilva\\Desktop\\PDF") );
		
		lfact.forEach
		( 
			t -> 
				{
					try 
					{
						fserv.crear( fdao.leer( t.getOid() ) );
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				} 
		);
		
		/*
		TFactura fct				= fdao.leer(2897);
		TCliente cli				= fct.getTCliente();
	    
		File file 					= new File( classLoader.getResource( fct.getTEmpresa().getJasper() +  ".jasper" ).getFile() );
		FileInputStream is			= new FileInputStream(file);
		
		double base					= fct.getBase();
		double biva					= fct.getBaseIva();
		
		List<FctLineaFb> laux		= fct.getTFacturaLineaOrd().stream().map( t -> new FctLineaFb( t.getCantidad(), t.getDescripcion(), t.getPrecio(), t.getDescuento() ) ).collect( Collectors.toList() );
		
		param.put( "nombre", cli.getNombre() + " " + cli.getApellidos() );
		param.put( "direccionCli", cli.getDireccion() );
		param.put( "fecha", fct.getFecha() );
		param.put( "numero", fct.getNumero() );
		param.put( "lineas", new FctLineaArray(laux) );
		param.put( "base", base );
		param.put( "iva", fct.getIva() );
		param.put( "biva", biva );
		param.put( "total", base + biva );
		
		JasperPrint fis 			= JasperFillManager.fillReport( is, param, new JREmptyDataSource() );

		JasperExportManager.exportReportToPdfFile( fis, "C:\\Users\\jsilva\\Desktop\\f.pdf" );
		*/
		
		System.out.println(1);
	}

}
