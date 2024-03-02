package com.atz.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRTemplatePrintText;

public abstract class PdfContrato 
{
	private static final String CONTADOR_PAGINAS = "CONTADOR_PAGINAS";
	
	/**
	 * Crea el report en la ubicación indicada por f.
	 * @param f			Destino del PDF.
	 * @param param		Datos a mostrar en el PDF.
	 * @return
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	protected JasperPrint getJasperPrint(String f, Map<String, Object> param) 
	throws FileNotFoundException, JRException
	{
		ClassLoader classLoader 	= this.getClass().getClassLoader();
		File file 					= new File( classLoader.getResource( f + ".jasper" ).getFile() );
		FileInputStream is			= new FileInputStream(file);
		
		return JasperFillManager.fillReport( is, param, new JREmptyDataSource() );		
	}
	
	/**
	 * Añade el contador de páginas.
	 * Cambia el token CONTADOR_PAGINAS por Página X de Y.
	 * @param pages	Páginas del informe
	 */
	protected void addPageCounter(List<JRPrintPage> pages) 
	{
		int currentPage = 1;
		
		pagina: for( JRPrintPage jpp : pages ) 
		{
			List<JRPrintElement> textos = jpp.getElements().stream().filter( t -> t instanceof JRTemplatePrintText ).collect( Collectors.toList() );
			
			for( JRPrintElement jpe : textos ) 
			{
				JRPrintText jpt	= (JRTemplatePrintText) jpe;
				String valor	= jpt == null ? null : jpt.getFullText();
				
				if( PdfContrato.CONTADOR_PAGINAS.equals(valor) ) 
				{
					jpt.setText( currentPage + " de " + pages.size() );
					currentPage++;
					continue pagina;
				}
			}
		}
	}
	
	protected void merge(JasperPrint dst, JasperPrint ... fuente)
	{
		for(JasperPrint f : fuente)
		{
			f.getPages().forEach( t -> dst.addPage(t) );
		}
	}
	
	protected void merge(List<JasperPrint> fuente) {
		for(int i = 1; i < fuente.size(); i++) {
			fuente.get(i).getPages().forEach( t -> fuente.get(0).addPage(t) );
		}
	}
	
	/**
	 * Elementos de una página en blanco:
	 * 1. JRTemplatePrintImage		Logo Atienza
	 * 2. JRTemplatePrintImage		Logo OCA
	 * 3. JRTemplatePrintRectangle	Pie de página
	 * 4. JRTemplatePrintText		Frase del pie de página
	 * 5. JRTemplatePrintText		X de Y
	 * 
	 * http://mattjiang.blogspot.com/2007/05/easy-way-to-remove-blank-page-generated.html
	 * https://stackoverflow.com/questions/42426311/how-to-recalculate-page-number-when-combining-multiple-jasper-reports-in-export
	 * 
	 * @param pages	Páginas del PDF
	 */
	protected void removeBlankPage(List<JRPrintPage> pages, int nElem) 
	{
		for( Iterator<JRPrintPage> i = pages.iterator(); i.hasNext(); ) 
		{
			JRPrintPage page = i.next();
			
			if( page.getElements().size() <= nElem )
			{
				i.remove();
			}
		}
	}
	
	protected void removeBlankPageParte(List<JRPrintPage> pages) 
	{
		for( Iterator<JRPrintPage> i = pages.iterator(); i.hasNext(); ) 
		{
			JRPrintPage page = i.next();
			
			if( page.getElements().size() <= 28 )
			{
				i.remove();
			}
		}
	}
}
