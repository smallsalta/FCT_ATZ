package com.atz.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atz.fb.ContratosBuscarFb;
import com.atz.pdf.FctLineaArray;
import com.atz.pdf.FctLineaFb;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TFactura;
import com.atz.persistencia.TFacturaLinea;

import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Data
public class PdfFacturaService 
extends PdfContrato
{
	@Autowired
	private FacturaService fservice;
	
	@Autowired
	@Qualifier("fctFolder")
	private File pdfFolder;
	
	public File crear(ContratosBuscarFb fb) 
	throws FileNotFoundException, JRException 
	{
		TFactura c = this.fservice.leer( fb.getOid() );
		
		return this.crearComun(c);
	}
	
	public File crear(TFactura c) 
	throws FileNotFoundException, JRException 
	{
		return this.crearComun(c);
	}
	
	public File leer(ContratosBuscarFb fb)
	{
		TFactura c		= this.fservice.leer( fb.getOid() );
		String pdfFile 	= this.pdfFolder.getAbsolutePath() + "/" + c.getNumero2() + ".pdf";
		File fpdf		= new File(pdfFile);
		
		if( !fpdf.exists() )
		{
			pdfFile 	= this.pdfFolder.getAbsolutePath() + "/error.pdf";
			fpdf		= new File(pdfFile);
		}
		
		return fpdf;
	}
	
	private File crearComun(TFactura c) 
	throws FileNotFoundException, JRException 
	{
		Map<String, Object> param	= new HashMap<>();
		List<Integer> oids			= Arrays.asList( 9 );
//		List<Integer> oids			= Arrays.asList( 9, 10 );
//		List<Integer> oids			= Arrays.asList( 8, 9, 10 );
		
		TCliente cli				= c.getTCliente();
		String pdfFile				= this.pdfFolder.getAbsolutePath() + "/" + c.getNumero2() + ".pdf";
		
		List<FctLineaFb> laux		= c.getTFacturaLineaOrd().stream().map( t -> this.copy(t) ).collect( Collectors.toList() );
		
		double base					= c.getBase();
		double biva					= c.getBaseIva();
		double total				= base + biva;
		double ajuste				= c.getAjuste() == null ? 0D : c.getAjuste();
		
		StringBuffer cliente		= new StringBuffer();
		
		JasperPrint is1				= null;
		JasperPrint is2				= null;
		JasperPrint is3				= null;
		
		cliente.append( cli.getNombre() );
		cliente.append(" ");
		cliente.append( cli.getApellidos() );
		cliente.append("\n");
		cliente.append( cli.getEmail() );
		cliente.append("\n");
		cliente.append( cli.getDni() );
		cliente.append("\n");
		cliente.append( cli.getDireccion() );
		cliente.append("\n");
		cliente.append( cli.getCp() );
		cliente.append(" ");
		cliente.append( cli.getLocalidad() );
		
		param.put( "cliente", cliente.toString() );
		param.put( "fecha", c.getFecha() );
		param.put( "numero", c.getNumero() );
		param.put( "lineas", new FctLineaArray(laux) );
		param.put( "base", base );
		param.put( "iva", c.getIva() );
		param.put( "biva", biva );
		param.put( "total", total );
		param.put( "ajuste", ajuste );
		param.put( "totaltotal", total - ajuste );
		
		if( oids.contains( c.getTEmpresa().getOid() ) )
		{
		
			is1	= this.getJasperPrint( "portada", param );
			is2	= this.getJasperPrint( c.getTEmpresa().getJasper(), param );
			is3	= this.getJasperPrint( "contraportada", new HashMap<>() );
			
			this.merge( is1, is2, is3 );
			this.addPageCounter( is1.getPages() );
		}
		else
		{
			is1	= this.getJasperPrint( c.getTEmpresa().getJasper(), param );
		}
		
		JasperExportManager.exportReportToPdfFile( is1, pdfFile );

		return new File(pdfFile);
	}	
	
//	private JasperPrint getJasperPrint(String f, Map<String, Object> param) 
//	throws FileNotFoundException, JRException
//	{
//		ClassLoader classLoader 	= this.getClass().getClassLoader();
//		File file 					= new File( classLoader.getResource( f + ".jasper" ).getFile() );
//		FileInputStream is			= new FileInputStream(file);
//
//		return JasperFillManager.fillReport( is, param, new JREmptyDataSource() );		
//	}
	
	private FctLineaFb copy(TFacturaLinea p)
	{
		return new FctLineaFb( p.getCantidad(), p.getDescripcion(), p.getPrecio(), p.getDescuento() );
	}
	
	public File getPdfFolder() 
	{
		return this.pdfFolder;
	}
}
