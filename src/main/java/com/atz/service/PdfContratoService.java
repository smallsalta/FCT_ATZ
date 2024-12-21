package com.atz.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atz.fb.ContratosBuscarFb;
import com.atz.pdf.ExtintorFb;
import com.atz.pdf.ExtintorFbArray;
import com.atz.pdf.PreguntaFb;
import com.atz.pdf.PreguntaFbArray;
import com.atz.persistencia.TAgente;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TLineaContrato;
import com.atz.persistencia.TPreguntas;
import com.atz.persistencia.TPreguntasContrato;
import com.atz.persistencia.TPreguntasRespuestas;
import com.atz.persistencia.TPrueba;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class PdfContratoService 
extends PdfContrato
{
	@Autowired
	private ContratoService kservice;
	
	@Autowired
	private PreguntaService qservice;
	
	@Autowired
	@Qualifier("pdfFolder")
	private File pdfFolder;
	
	@Autowired
	@Qualifier("contratoJasper")
	private String contratoJasper;
	
	private Map<Integer, String> magentes;
	private Map<Integer, String> mpruebas;
	private Map<Integer, String> mrespuestas;
	private Map<Integer, String> mpreguntas;
	
	public File crear(ContratosBuscarFb fb) 
	throws FileNotFoundException, JRException 
	{
		TContrato c = this.kservice.leer( fb.getOid() );
		
		return this.crearComun(c);
	}
	
	public File crear(TContrato c) 
	throws FileNotFoundException, JRException 
	{
		return this.crearComun(c);
	}
	
	public File leer(ContratosBuscarFb fb)
	{
		TContrato c		= this.kservice.leer( fb.getOid() );
//		String pdfFile 	= this.pdfFolder.getAbsolutePath() + "/" + c.getOid() + "_" + c.getNumero() + ".pdf";
//		String pdfFile 	= this.pdfFolder.getAbsolutePath() + "/" + c.getOid() + ".pdf";
		String pdfFile 	= this.pdfFolder.getAbsolutePath() + "/" + c.getNumero() + ".pdf";
		File fpdf		= new File(pdfFile);
		
		if( !fpdf.exists() )
		{
			pdfFile 	= this.pdfFolder.getAbsolutePath() + "/error.pdf";
			fpdf		= new File(pdfFile);
		}
		
		return fpdf;
	}
	
	private File crearComun(TContrato c) 
	throws FileNotFoundException, JRException 
	{
		this.magentes				= this.kservice.leerAgentes().stream().collect( Collectors.toMap( TAgente::getOid, TAgente::getDescr ) );
		this.mpruebas				= this.kservice.leerPruebas().stream().collect( Collectors.toMap( TPrueba::getOid, TPrueba::getDescr ) );
		this.mrespuestas			= this.qservice.getRespuestas().stream().collect( Collectors.toMap( TPreguntasRespuestas::getOid, TPreguntasRespuestas::getDescr ) );
		this.mpreguntas				= this.qservice.getPreguntas().stream().collect( Collectors.toMap( TPreguntas::getOid, TPreguntas::getDescr ) );
		
		Map<String, Object> param	= new HashMap<>();
		ExtintorFbArray fbe			= new ExtintorFbArray();
		PreguntaFbArray fbp			= new PreguntaFbArray();
		
		TCliente cl					= c.getTCliente();
		double cantidad				= c.getTLineaContratos().stream().filter( t -> t.getPrecio() != null ).mapToDouble( TLineaContrato::getPrecio ).sum();
		String pdfFile				= this.pdfFolder.getAbsolutePath() + "/" + c.getNumero() + ".pdf";

		this.copy(fbe, c);
		this.copy(fbp, c);
		
		param.put( "nombre", cl.getNombre() + " " + cl.getApellidos() );
		param.put( "direccionCli", cl.getDireccion() + ", " + cl.getLocalidad() + " (" + cl.getProvincia() + ")" );
		param.put( "fecha", c.getFecha() );
		param.put( "numero", c.getNumero() );
		param.put( "direccionCert", c.getDireccion() );
		param.put( "extintores", fbe );
		param.put( "preguntas", fbp );
		param.put( "anexo", c.getAnexo() );
		param.put( "precio", cantidad );
		param.put( "trimestral", c.getTrimestral() );
		param.put( JRParameter.REPORT_LOCALE, new Locale("es", "ES") );
		param.put( "modalidad", c.getTParteModalidad().getDescripcion() );
		param.put( "periodicidad", c.getTPartePeriodicidad().getDescripcion() );		
		
		JasperPrint fis1	= this.getJasperPrint( "atz1" + this.contratoJasper, param );
		JasperPrint fis2	= this.getJasperPrint( "atz2" + this.contratoJasper, param );
//		JasperPrint fis3 	= this.getJasperPrint( "atz3" + this.contratoJasper, param );
		JasperPrint fis4 	= this.getJasperPrint( "atz4" + this.contratoJasper, param );
		
//		this.merge( fis1, fis2, fis3, fis4 );
		this.merge( fis1, fis2, fis4 );
		this.removeBlankPage( fis1.getPages(), 5 );
		this.addPageCounter( fis1.getPages() );
		
		JasperExportManager.exportReportToPdfFile(fis1, pdfFile);
		
		return new File(pdfFile);
	}
	
	private void copy(ExtintorFbArray fb, TContrato c)
	{
		List<ExtintorFb> lext = new LinkedList<>();
		
		for( TLineaContrato l : c.getTLineaContratosOrd() )
		{
			ExtintorFb ext	= new ExtintorFb();
			
			TAgente ta		= l.getTAgente();
			TPrueba tp		= l.getTPrueba();
			
			int oidTa		= ta == null ? 1 : ta.getOid();	// ABC
			int oidTp		= tp == null ? 4 : tp.getOid();	// N
			
			Double dcap		= l.getCapacidad();
			
			float fcap		= dcap == null ? 0F :dcap.floatValue();
			
			ext.setAgente( this.magentes.get(oidTa) );
			ext.setCantidad( l.getCantidad() );
			ext.setCapacidad( fcap );
			ext.setFabricante( l.getFabricante() );
			ext.setFFabricacion( l.getFechaFab() );
			ext.setFRetimbrado( l.getFechaRet() );
			ext.setPrueba( this.mpruebas.get(oidTp) );
			ext.setNplaca( l.getNumeroPlaca() );
			ext.setDescripcion( l.getDescr() );
			
			lext.add(ext);
		}
		
		fb.setExtintores( new ArrayList<>(lext) );
	}
	
	private void copy(PreguntaFbArray fb, TContrato c)
	{
		List<PreguntaFb> lext = new LinkedList<>();
		
		for( TPreguntasContrato p : c.getTPreguntasContratosOrd() )
		{
			PreguntaFb pr = new PreguntaFb();
			
			pr.setPregunta( this.mpreguntas.get( p.getTPreguntas().getOid() ) );
			pr.setRespuesta( this.mrespuestas.get( p.getTPreguntasRespuestas().getOid() ) );
			
			lext.add(pr);
		}
		
		fb.setPreguntas( new ArrayList<>(lext) );
	}

	public File getPdfFolder() 
	{
		return this.pdfFolder;
	}
}
