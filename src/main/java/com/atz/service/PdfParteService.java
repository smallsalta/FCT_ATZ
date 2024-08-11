package com.atz.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atz.comparator.CentralComparator;
import com.atz.comparator.DetectoresComparator;
import com.atz.comparator.EquipoAuxiliarComparator;
import com.atz.comparator.FuenteComparator;
import com.atz.comparator.PuertasComparator;
import com.atz.comparator.PulsadoresComparator;
import com.atz.comparator.RetenedorComparator;
import com.atz.comparator.SirenasComparator;
import com.atz.fb.PartesFb;
import com.atz.pdf.ParteLineaFb;
import com.atz.pdf.ParteLineaFbArray;
import com.atz.pdf.PreguntaFb;
import com.atz.pdf.PreguntaFbArray;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TMatrimonio;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteLinea;
import com.atz.persistencia.TPreguntas;
import com.atz.persistencia.TPreguntasParte;
import com.atz.persistencia.TPreguntasRespuestas;
import com.atz.persistencia.TUsuario;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class PdfParteService extends PdfContrato {

	@Autowired
	private ParteService pservice;
	
	@Autowired
	private UsuarioService uservice;
	
	@Autowired
	private MatrimonioService mservice;
	
	@Autowired
	private ContratoService cservice;
	
	@Autowired
	@Qualifier("parteFolder")
	private File parteFolder;
	
	@Autowired
	@Qualifier("contratoJasper")
	private String contratoJasper;
	
	@Autowired
	private PreguntaService qservice;
	
	private Map<Integer, String> mrespuestas;
	private Map<Integer, String> mpreguntas;
	
	public File crear(PartesFb fb) 
	throws FileNotFoundException, JRException{
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComun(p, 0);
	}
	
	public File crear1(PartesFb fb) 
	throws FileNotFoundException, JRException{
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComun(p, 1);
	}
	
	public File crear2(PartesFb fb) 
	throws FileNotFoundException, JRException{
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComun(p, 2);
	}
	

	public File chapuza(PartesFb fb) throws FileNotFoundException, JRException, IllegalAccessException, InvocationTargetException, ParseException {
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComunC(p, 0);
	}
	
	public File chapuza1(PartesFb fb) throws FileNotFoundException, JRException, IllegalAccessException, InvocationTargetException, ParseException {
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComunC(p, 1);
	}
	
	public File chapuza2(PartesFb fb) throws FileNotFoundException, JRException, IllegalAccessException, InvocationTargetException, ParseException {
		TParte p = this.pservice.leer(fb.getOid());
		
		return this.crearComunC(p, 2);
	}
	
	private File crearComunC(TParte p, int pdfCentral) 
	throws FileNotFoundException, JRException, IllegalAccessException, InvocationTargetException, ParseException 
	{	
		TCliente cl					= p.getTCliente();
		TMatrimonio tm				= this.mservice.leer( Arrays.asList(p) ).get(0);
		TContrato tc				= this.cservice.leer2( tm.getContrato() );
		
		Map<String, Object> param	= new HashMap<>();
		ParteLineaFbArray pla		= new ParteLineaFbArray();
		
		String pdfFile				= this.parteFolder.getAbsolutePath() + "/" + tm.getContrato() + ".pdf";
		JasperPrint parte 			= null;
		NumberFormat dec			= new DecimalFormat( "###,##0.00", new DecimalFormatSymbols(Locale.GERMAN) );
		
		param.put( "dni", p.getDni() == null ? "" : p.getDni() );
		param.put( "usuario", this.makeUsuario( p.getTUsuario() ) );
		
		if(p.getTParteTipo().getOid() == 5 || p.getTParteTipo().getOid() == 6) 
		{
			ParteLineaFbArray plcentral = new ParteLineaFbArray();
			ParteLineaFbArray pldetectores = new ParteLineaFbArray();
			ParteLineaFbArray plfuente = new ParteLineaFbArray();
			ParteLineaFbArray plpulsadores = new ParteLineaFbArray();
			ParteLineaFbArray plsirenas = new ParteLineaFbArray();
			ParteLineaFbArray plequipoauxiliar = new ParteLineaFbArray();
			ParteLineaFbArray plretenedor = new ParteLineaFbArray();
			ParteLineaFbArray plpuertas = new ParteLineaFbArray();
			
			this.copyCentralita(plcentral, p.getTParteLineas().stream().filter(x -> x.getOrdenCentral() != null && x.getOrdenCentral() > 0).sorted(new CentralComparator()).collect(Collectors.toList()), 1);
			this.copyCentralita(plfuente, p.getTParteLineas().stream().filter(x -> x.getOrdenFuente() != null && x.getOrdenFuente() > 0).sorted(new FuenteComparator()).collect(Collectors.toList()), 2);
			this.copyCentralita(pldetectores, p.getTParteLineas().stream().filter(x -> x.getOrdenDetectores() != null && x.getOrdenDetectores() > 0).sorted(new DetectoresComparator()).collect(Collectors.toList()), 3);
			this.copyCentralita(plpulsadores, p.getTParteLineas().stream().filter(x -> x.getOrdenPulsadores() != null && x.getOrdenPulsadores() > 0).sorted(new PulsadoresComparator()).collect(Collectors.toList()), 4);
			this.copyCentralita(plsirenas, p.getTParteLineas().stream().filter(x -> x.getOrdenSirenas() != null && x.getOrdenSirenas() > 0).sorted(new SirenasComparator()).collect(Collectors.toList()), 5);
			this.copyCentralita(plequipoauxiliar, p.getTParteLineas().stream().filter(x -> x.getOrdenEquipoAuxiliar() != null && x.getOrdenEquipoAuxiliar() > 0).sorted(new EquipoAuxiliarComparator()).collect(Collectors.toList()), 6);
			this.copyCentralita(plretenedor, p.getTParteLineas().stream().filter(x -> x.getOrdenRetenedor() != null && x.getOrdenRetenedor() > 0).sorted(new RetenedorComparator()).collect(Collectors.toList()), 7);
			this.copyCentralita(plpuertas, p.getTParteLineas().stream().filter(x -> x.getOrdenPuertas() != null && x.getOrdenPuertas() > 0).sorted(new PuertasComparator()).collect(Collectors.toList()), 8);
			
			param.put("precio_total", this.calculaTotalCentralita(p.getTParteLineas()));
			param.put("partelineascentral", plcentral);
			param.put("partelineasfuente", plfuente);
			param.put("partelineasdetectores", pldetectores);
			param.put("partelineaspulsadores", plpulsadores);
			param.put("partelineassirenas", plsirenas);
			param.put("partelineasequipoauxiliar", plequipoauxiliar);
			param.put("partelineasretenedor", plretenedor);
			param.put("partelineaspuertas", plpuertas);
			
			if(pdfCentral == 1) 
			{
				parte = this.getJasperPrint("centralita1_c", param);
				JasperPrint c2 = this.getJasperPrint("centralita2_c", param);
				JasperPrint c3 = this.getJasperPrint("centralita3_1_c", param);
				this.merge(parte, c2, c3);	
			} 
			else if(pdfCentral == 2)
			{
				parte = this.getJasperPrint("centralita3_2_c", param);
				JasperPrint c2 = this.getJasperPrint("centralita4_c", param);
				this.merge(parte, c2);
			}
			
			this.removeBlankPageParteC(parte.getPages());
		} 
		else 
		{	
			this.copy(pla, p);
			
			param.put("partelineas", pla);
			
			// No sé qué pasa con el resto de partes ...
			if(p.getTParteTipo().getOid() == 1 || p.getTParteTipo().getOid() == 2 || p.getTParteTipo().getOid() == 4) 
			{
				String pt 			= this.calculaTotal( p.getTParteLineas() );
				double precio 		= ( p.getCmto() != null && p.getCmto() != 0 ) ? p.getCmto() : dec.parse(pt).doubleValue();
				
				param.put( "precio_total", dec.format(precio) );
			}
			
			parte = this.getJasperPrint(this.getJasperFromTipo(p.getTParteTipo().getOid()) + "_c", param);

			this.removeBlankPageParte(parte.getPages());	
		}
		
		param.put( "nombre", cl.getNombre() + " " + cl.getApellidos() );
		param.put( "direccionCli", cl.getDireccion() + ", " + cl.getLocalidad() + " (" + cl.getProvincia() + ")" );
		param.put( "fecha", p.getFecha() );
		param.put( "numero", tm.getContrato() );
		param.put( "anexo", tc.getAnexo() );
		param.put( "direccionCert", p.getDirtra() );
		param.put( "trimestral", tc.getTrimestral() );
		param.put( JRParameter.REPORT_LOCALE, new Locale("es", "ES") );
		param.put( "precio", dec.parse( param.get("precio_total").toString() ).doubleValue() );
		
		JasperPrint fis1	= this.getJasperPrint( "atz1" + this.contratoJasper + "_c" , param );
		JasperPrint fis2	= this.getJasperPrint( "atz2" + this.contratoJasper + "_c", param );
		JasperPrint fis4 	= this.getJasperPrint( "atz4" + this.contratoJasper + "_c", param );
		
		this.merge( fis1, parte, fis2, fis4 );
		this.removeBlankPage( fis1.getPages(), 6 );
		this.addPageCounter( fis1.getPages() );
		
		JasperExportManager.exportReportToPdfFile(fis1, pdfFile);
		
		return new File(pdfFile);
	}
	
	private File crearComun(TParte p, int pdfCentral) 
	throws FileNotFoundException, JRException{
		
		this.mpreguntas				= this.qservice.getPreguntas().stream().collect( Collectors.toMap( TPreguntas::getOid, TPreguntas::getDescr ) );
		this.mrespuestas			= this.qservice.getRespuestas().stream().collect( Collectors.toMap( TPreguntasRespuestas::getOid, TPreguntasRespuestas::getDescr ) );
		
		Map<String, Object> param	= new HashMap<>();
		ParteLineaFbArray pla		= new ParteLineaFbArray();
		PreguntaFbArray fbp			= new PreguntaFbArray();
		String pdfFile				= this.parteFolder.getAbsolutePath() + "/" + p.getNumero() + ".pdf";
		
		param.put("nombre", p.getTCliente().getNombre() + " " + p.getTCliente().getApellidos());
		param.put("fecha", p.getFecha());
		param.put("numero", p.getNumero());
		param.put("cliente_direccion", this.makeClienteDireccion(p.getTCliente()));
		param.put("cliente_telefono", p.getTCliente().getTelefono1() == null ? "" : p.getTCliente().getTelefono1().toString());
		param.put("cliente_correo", p.getTCliente().getEmail() == null ? "" : p.getTCliente().getEmail());
		param.put("cliente_dni", p.getTCliente().getDni() == null ? "" : p.getTCliente().getDni());
		param.put( JRParameter.REPORT_LOCALE, new Locale("es", "ES") );
		param.put("dni", p.getDni() == null ? "" : p.getDni());
		param.put("usuario", this.makeUsuario(p.getTUsuario()));
		param.put("estado", p.getEstado2().getDescr() );
		param.put("anterior", p.getAnterior() );
		
		this.copy(fbp, p);
		param.put("preguntas", fbp);
		
		if(p.getTParteTipo().getOid() == 5 || p.getTParteTipo().getOid() == 6) {
			ParteLineaFbArray plcentral = new ParteLineaFbArray();
			ParteLineaFbArray pldetectores = new ParteLineaFbArray();
			ParteLineaFbArray plfuente = new ParteLineaFbArray();
			ParteLineaFbArray plpulsadores = new ParteLineaFbArray();
			ParteLineaFbArray plsirenas = new ParteLineaFbArray();
			ParteLineaFbArray plequipoauxiliar = new ParteLineaFbArray();
			ParteLineaFbArray plretenedor = new ParteLineaFbArray();
			ParteLineaFbArray plpuertas = new ParteLineaFbArray();
			
			this.copyCentralita(plcentral, p.getTParteLineas().stream().filter(x -> x.getOrdenCentral() != null && x.getOrdenCentral() > 0).sorted(new CentralComparator()).collect(Collectors.toList()), 1);
			this.copyCentralita(plfuente, p.getTParteLineas().stream().filter(x -> x.getOrdenFuente() != null && x.getOrdenFuente() > 0).sorted(new FuenteComparator()).collect(Collectors.toList()), 2);
			this.copyCentralita(pldetectores, p.getTParteLineas().stream().filter(x -> x.getOrdenDetectores() != null && x.getOrdenDetectores() > 0).sorted(new DetectoresComparator()).collect(Collectors.toList()), 3);
			this.copyCentralita(plpulsadores, p.getTParteLineas().stream().filter(x -> x.getOrdenPulsadores() != null && x.getOrdenPulsadores() > 0).sorted(new PulsadoresComparator()).collect(Collectors.toList()), 4);
			this.copyCentralita(plsirenas, p.getTParteLineas().stream().filter(x -> x.getOrdenSirenas() != null && x.getOrdenSirenas() > 0).sorted(new SirenasComparator()).collect(Collectors.toList()), 5);
			this.copyCentralita(plequipoauxiliar, p.getTParteLineas().stream().filter(x -> x.getOrdenEquipoAuxiliar() != null && x.getOrdenEquipoAuxiliar() > 0).sorted(new EquipoAuxiliarComparator()).collect(Collectors.toList()), 6);
			this.copyCentralita(plretenedor, p.getTParteLineas().stream().filter(x -> x.getOrdenRetenedor() != null && x.getOrdenRetenedor() > 0).sorted(new RetenedorComparator()).collect(Collectors.toList()), 7);
			this.copyCentralita(plpuertas, p.getTParteLineas().stream().filter(x -> x.getOrdenPuertas() != null && x.getOrdenPuertas() > 0).sorted(new PuertasComparator()).collect(Collectors.toList()), 8);
			
			param.put("precio_total", this.calculaTotalCentralita(p.getTParteLineas()));
			param.put("partelineascentral", plcentral);
			param.put("partelineasfuente", plfuente);
			param.put("partelineasdetectores", pldetectores);
			param.put("partelineaspulsadores", plpulsadores);
			param.put("partelineassirenas", plsirenas);
			param.put("partelineasequipoauxiliar", plequipoauxiliar);
			param.put("partelineasretenedor", plretenedor);
			param.put("partelineaspuertas", plpuertas);
			
			JasperPrint c1 = new JasperPrint();
			JasperPrint jPreguntas	= this.getJasperPrint("parte_preguntas", param);
			
			if(pdfCentral == 1) {
				c1 = this.getJasperPrint("centralita1", param);
				JasperPrint c2 = this.getJasperPrint("centralita2", param);
				JasperPrint c3 = this.getJasperPrint("centralita3_1", param);
				this.merge(c1, c2, c3, jPreguntas);
				
			} else if(pdfCentral == 2) {
				c1 = this.getJasperPrint("centralita3_2", param);
				JasperPrint c2 = this.getJasperPrint("centralita4", param);
				this.merge(c1, c2, jPreguntas);
				
			}
			
			if(p.getObservaciones()!= null && !p.getObservaciones().equals("")) {
				param.put("observaciones", p.getObservaciones());
				JasperPrint po = this.getJasperPrint(p.getTParteTipo().getOid() == 4 ? "parte_observaciones_observaciones" : "parte_observaciones", param);

				this.merge(c1, po);
			} 
			
			this.removeBlankPageParte(c1.getPages());
			this.addPageCounter(c1.getPages());
			
			JasperExportManager.exportReportToPdfFile(c1, pdfFile);
			
		} else {
			
			if(p.getTParteTipo().getOid() == 2) {
				this.copyBie(pla, p, 0);
				param.put("partelineas", pla);
				
			} else {
				this.copy(pla, p);
				
				param.put("partelineas", pla);
			}
			
			
			
			if(p.getTParteTipo().getOid() == 1 || p.getTParteTipo().getOid() == 2 || p.getTParteTipo().getOid() == 4) {
				param.put("precio_total", this.calculaTotal(p.getTParteLineas()));
			}
			
			JasperPrint jp 			= this.getJasperPrint(this.getJasperFromTipo(p.getTParteTipo().getOid()), param);
			if(p.getTParteTipo().getOid() == 2) {
				ParteLineaFbArray plaB = new ParteLineaFbArray();
				this.copyBie(plaB, p, 1);
				param.put("partelineasbomba", plaB);
				JasperPrint jpb = this.getJasperPrint("parte2_bomba", param);
				this.merge(jp, jpb);
			}
			JasperPrint jPreguntas	= this.getJasperPrint("parte_preguntas", param);
			
			// this.removeBlankPageParte(jp.getPages());
			
			if(p.getObservaciones()!= null && !p.getObservaciones().equals("")) {
				param.put("observaciones", p.getObservaciones());
				JasperPrint po = this.getJasperPrint(p.getTParteTipo().getOid() == 4 ? "parte_observaciones_observaciones" : "parte_observaciones", param);	
				this.merge(jp, jPreguntas, po);
			} else {
				this.merge(jp, jPreguntas);
			}
			
			this.removeBlankPageParte(jp.getPages());
			this.addPageCounter(jp.getPages());
			
			JasperExportManager.exportReportToPdfFile(jp, pdfFile);
		}
		
		return new File(pdfFile);
		
	}
	
	/**
	 * Tipo 1 = Central
	 * Tipo 2 = Fuente
	 * Tipo 3 = Detectores
	 * Tipo 4 = Pulsadores
	 * Tipo 5 = Sirenas
	 * 
	 */
	private void copyCentralita(ParteLineaFbArray pla, List<TParteLinea> tpl, int tipo) {
		List<ParteLineaFb> lin = new ArrayList<>();
		switch (tipo) {
		case 1:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setMarca(this.toStringField(l.getMarca()));
				p.setPilotos(this.toStringField(l.getPilotos()));
				p.setTipoBateriaCentral(this.toStringField(l.getTipoBateriaCentral()));
				p.setTipoCentral(this.toStringField(l.getTipoCentral()));
				p.setModelo(this.toStringField(l.getModelo()));
				p.setReles(this.toStringField(l.getReles()));
				p.setUnidades(this.toStringField(l.getUnidades()));
				p.setNZona(this.toStringField(l.getnZona()));
				p.setZumbador(this.toStringField(l.getZumbador()));
				p.setZonas(this.toStringField(l.getZonas()));
				p.setMandosControl(this.toStringField(l.getMandosControl()));
				p.setLineasCableado(this.toStringField(l.getLineasCableado()));
				p.setZonasReserva(this.toStringField(l.getZonasReserva()));
				p.setRedIndep(this.toStringField(l.getRedIndep()));
				p.setTemporizador(this.toStringField(l.getTemporizador()));
				p.setEstadoCargaCentral(this.toStringField(l.getEstadoCargaCentral()));
				p.setPrecioCentral(this.toStringField(l.getPrecioCentral()));
				
				lin.add(p);
			} 
			break;
		case 2:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setMarchar(this.toStringField(l.getMarchar()));
				//p.setModeloFuente(this.toStringField(l.getModeloFuente()));
				//p.setCorriente(this.toStringField(l.getCorriente()));
				p.setTipoBateriaFuente(this.toStringField(l.getTipoBateriaFuente()));
				p.setEstadoCargaFuente(this.toStringField(l.getEstadoCargaFuente()));
				p.setPrecioFuente(this.toStringField(l.getPrecioFuente()));
				p.setCantidadLuminaria(this.toStringField(l.getCantidadLuminaria()));
				p.setUbicacionLuminaria(this.toStringField(l.getUbicacionLuminaria()));
				p.setAnoLuminaria(this.toStringField(l.getAnoLuminaria()));
				p.setLumenes(this.toStringField(l.getLumenes()));
				
				lin.add(p);
			} 
			break;
		case 3:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setUbiDetectores(this.toStringField(l.getUbiDetectores()));
				p.setTipoDetectores(this.toStringField(l.getTipoDetectores()));
				p.setMarcaDetectores(this.toStringField(l.getMarcaDetectores()));
				p.setFuncionDetectores(this.toStringField(l.getFuncionDetectores()));
				p.setAnoDetectores(this.toStringField(l.getAnoDetectores()));
				p.setZonaDetectores(this.toStringField(l.getZonaDetectores()));
				p.setEstadoDetectores(this.toStringField(l.getEstadoDetectores()));
				p.setPrecioDetectores(this.toStringField(l.getPrecioDetectores()));
				p.setCantidadDetectores(this.toStringField(l.getCantidadDetectores()));
				
				lin.add(p);
			}
			break;
		case 4:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setUbiPulsadores(this.toStringField(l.getUbiPulsadores()));
				p.setCantidadPulsadores(this.toStringField(l.getCantidadPulsadores()));
				p.setTipoPulsadores(this.toStringField(l.getTipoPulsadores()));
				p.setMarcaPulsadores(this.toStringField(l.getMarcaPulsadores()));
				p.setAnoPulsadores(this.toStringField(l.getAnoPulsadores()));
				p.setZonaPulsadores(this.toStringField(l.getZonaPulsadores()));
				p.setEstadoPulsadores(this.toStringField(l.getEstadoPulsadores()));
				p.setPrecioPulsadores(this.toStringField(l.getPrecioPulsadores()));
				
				lin.add(p);
			}
			break;
		case 5:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setUbiSirenas(this.toStringField(l.getUbiSirenas()));
				p.setCantidadSirenas(this.toStringField(l.getCantidadSirenas()));
				p.setTipoSirenas(this.toStringField(l.getTipoSirenas()));
				p.setMarcaSirenas(this.toStringField(l.getMarcaSirenas()));
				p.setAnoSirenas(this.toStringField(l.getAnoSirenas()));
				p.setZonaSirenas(this.toStringField(l.getZonaSirenas()));
				p.setEstadoSirenas(this.toStringField(l.getEstadoSirenas()));
				p.setPrecioSirenas(this.toStringField(l.getPrecioSirenas()));
				
				lin.add(p);
			}
			break;
		case 6:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setCantidadEquipoAuxiliar(this.toStringField(l.getCantidadEquipoAuxiliar()));
				p.setUbicacionEquipoAuxiliar(this.toStringField(l.getUbicacionEquipoAuxiliar()));
				p.setTipoEquipoAuxiliar(this.toStringField(l.getTipoEquipoAuxiliar()));
				p.setMarcaEquipoAuxiliar(this.toStringField(l.getMarcaEquipoAuxiliar()));
				p.setAnoEquipoAuxiliar(this.toStringField(l.getAnoEquipoAuxiliar()));
				p.setZonaEquipoAuxiliar(this.toStringField(l.getZonaEquipoAuxiliar()));
				p.setEstadoEquipoAuxiliar(this.toStringField(l.getEstadoEquipoAuxiliar()));
				p.setPrecioEquipoAuxiliar(this.toStringField(l.getPrecioEquipoAuxiliar()));
				
				lin.add(p);
			}
			break;
		case 7:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setCantidadRetenedor(this.toStringField(l.getCantidadRetenedor()));
				p.setUbiRetenedor(this.toStringField(l.getUbiRetenedor()));
				p.setMarcaRetenedor(this.toStringField(l.getMarcaRetenedor()));
				p.setAnoRetenedor(this.toStringField(l.getAnoRetenedor()));
				p.setEstadoRetenedor(this.toStringField(l.getEstadoRetenedor()));
				p.setPrecioRetenedor(this.toStringField(l.getPrecioRetenedor()));
				
				lin.add(p);
			}
			break;
		case 8:
			for(TParteLinea l : tpl) {
				ParteLineaFb p = new ParteLineaFb();
				
				p.setCantidadPuertas(this.toStringField(l.getCantidadPuertas()));
				p.setUbiPuertas(this.toStringField(l.getUbiPuertas()));
				p.setMarcaPuertas(this.toStringField(l.getMarcaPuertas()));
				p.setAnoPuertas(this.toStringField(l.getAnoPuertas()));
				p.setEstadoPuertas(this.toStringField(l.getEstadoPuertas()));
				p.setPrecioPuertas(this.toStringField(l.getPrecioPuertas()));
				
				lin.add(p);
			}
			break;
		default:
			break;
		}
		
		pla.setPartelineas(lin);
	}
	
	private String calculaTotal(List<TParteLinea> l) 
	{
		DecimalFormat dec = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
		
		return dec.format((Double)l.stream().filter(x -> x.getPrecio() != null).mapToDouble(x -> x.getPrecio()).sum());
	}
	
	private String calculaTotalCentralita(List<TParteLinea> l) {
		DecimalFormat dec = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
		
		Double totalCentral = (Double)l.stream().filter(x -> x.getPrecioCentral() != null).mapToDouble(x -> x.getPrecioCentral()).sum();
		Double totalFuente = (Double)l.stream().filter(x -> x.getPrecioFuente() != null).mapToDouble(x -> x.getPrecioFuente()).sum();
		Double totalDetectores = (Double)l.stream().filter(x -> x.getPrecioDetectores() != null).mapToDouble(x -> x.getPrecioDetectores()).sum();
		Double totalPulsadores = (Double)l.stream().filter(x -> x.getPrecioPulsadores() != null).mapToDouble(x -> x.getPrecioPulsadores()).sum();
		Double totalSirenas = (Double)l.stream().filter(x -> x.getPrecioSirenas() != null).mapToDouble(x -> x.getPrecioSirenas()).sum();
		Double totalEquipoAux = (Double)l.stream().filter(x -> x.getPrecioEquipoAuxiliar() != null).mapToDouble(x -> x.getPrecioEquipoAuxiliar()).sum();
		Double totalRetenedor = (Double)l.stream().filter(x -> x.getPrecioRetenedor() != null).mapToDouble(x -> x.getPrecioRetenedor()).sum();
		Double totalPuertas = (Double)l.stream().filter(x -> x.getPrecioPuertas() != null).mapToDouble(x -> x.getPrecioPuertas()).sum();
		
		return dec.format(totalCentral + totalFuente + totalDetectores + totalPulsadores + totalSirenas + totalEquipoAux + totalRetenedor + totalPuertas);

	}
	
	private String makeClienteDireccion(TCliente t) {
		String res = "";
		
		if(t.getDireccion() != null) {
			res = res + t.getDireccion() + ", ";
		}
		
		if(t.getLocalidad() != null) {
			res = res + t.getLocalidad() + ", ";
		}
		
		if(t.getProvincia() != null) {
			res = res + t.getProvincia() + ", ";
		}
		
		if(t.getCp() != null) {
			res = res + t.getCp();
		}
		
		return res;
	}
	
	private String makeUsuario(TUsuario u) {
		String res = "";
		
		if(u.getNombre() != null) {
			res = res + u.getNombre();
			if(u.getAppelidos() != null) {
				res = res + " " + u.getAppelidos();
			}
		}
		
		return res;
	}
	
	private void copy(PreguntaFbArray fb, TParte p) {
		List<PreguntaFb> lext = new LinkedList<>();
		
		for( TPreguntasParte pp : p.getTPreguntasParteOrd()) {
			PreguntaFb pr = new PreguntaFb();
			
			pr.setPregunta(this.mpreguntas.get(pp.getTPreguntas().getOid() ));
			pr.setRespuesta( this.mrespuestas.get( pp.getTPreguntasRespuestas().getOid() ) );
			
			lext.add(pr);
		}
		
		fb.setPreguntas( new ArrayList<>(lext) );
	}
	
	private void copy(ParteLineaFbArray pla, TParte tp) {
		List<ParteLineaFb> lin = new ArrayList<>();
		
		if(tp.getTParteTipo().getOid() == 1) {
			
			this.fillParteLineaExtintor(lin, tp.getTParteLineasOrd());
			
		} else if(tp.getTParteTipo().getOid() == 4) {
			
			this.fillParteLineaObservaciones(lin, tp.getTParteLineasOrd());
			
		}
		
		pla.setPartelineas(lin);
	}
	
	private void copyBie(ParteLineaFbArray pla, TParte tp, int pestanya) {
		List<ParteLineaFb> lin = new ArrayList<>();
		
		if(pestanya == 0) { // Pestaña BIE
			
			this.fillParteLineaBie(lin, tp.getTParteLineasBieOrd());
			
		} else if(pestanya == 1) { // Pestaña Bomba
			
			this.fillParteLineaBieBomba(lin, tp.getTParteLineasBieBombaOrd());
			
		}
		
		pla.setPartelineas(lin);
	}
	
	
	
	private void fillParteLineaObservaciones(List<ParteLineaFb> lin, List<TParteLinea> tpl) {
		for(TParteLinea l : tpl) {
			ParteLineaFb p = new ParteLineaFb();
			
			p.setOrden(this.toStringField(l.getOrden()));
			p.setPrecio(this.toStringField(l.getPrecio() == null ? .0d : l.getPrecio()));
			p.setCantidad(this.toStringField(l.getCantidad()));
			p.setDescripcion(this.toStringField(l.getDescripcion()));
			
			lin.add(p);
		}
	}
	
	private void fillParteLineaExtintor(List<ParteLineaFb> lin, List<TParteLinea> tpl) {
		for(TParteLinea l : tpl) {
			ParteLineaFb p = new ParteLineaFb();
			
			p.setOrden(this.toStringField(l.getOrden()));
			p.setUbicacion(l.getUbicacion());
			p.setTipo(this.toStringField(l.getTipo().getTipo()));
			
			p.setNumPlaca(this.toStringField(l.getNumPlaca()));
			p.setKg(this.toStringField(l.getKg()));
			p.setFabricante(l.getFabricante());
			p.setFechaFabricacion(l.getFechaFabricacion());
			p.setUltimoRetimbrado(l.getUltimoRetimbrado());
			p.setRv(this.booleanToString(l.getRv()));
			p.setRc(this.booleanToString(l.getRc()));
			p.setRt(this.booleanToString(l.getRt()));
			p.setNuevo(this.booleanToString(l.getNuevo()));
			p.setCartel(l.getCartel());
			p.setAltura(this.toStringField(l.getAltura()));
			p.setPrecio(this.toStringField(l.getPrecio() == null ? .0d : l.getPrecio()));
			
			lin.add(p);
		}
	}
	
	private void fillParteLineaBie(List<ParteLineaFb> lin, List<TParteLinea> tpl) {
		for(TParteLinea l : tpl) {
			ParteLineaFb p = new ParteLineaFb();
			
			p.setOrden(this.toStringField(l.getOrden()));
			p.setUbicacion(this.toStringField(l.getUbicacion()));
			p.setTipo(this.toStringField(l.getTipoBie().getTipo()));
			
			p.setLongMang(this.toStringField(l.getLongMang()));
			p.setFechaRetimA(l.getFechaRetimA());
			p.setFechaRetimB(l.getFechaRetimB());
			p.setPresionEstatica(this.toStringField(l.getPresionEstatica()));
			p.setPresionDinamica(this.toStringField(l.getPresionDinamica()));
			p.setManguera(l.getManguera());
			p.setLanza(l.getLanza());
			p.setValvula(l.getValvula());
			p.setManometro(l.getManometro());
			p.setCristal(l.getCristal());
			p.setSennales(l.getSennales());
			p.setEstadoGeneral(l.getEstadoGeneral());
			p.setPrecio(this.toStringField(l.getPrecio() == null ? .0d : l.getPrecio()));
			p.setNumSerie(this.toStringField(l.getNumSerie()));
			
			lin.add(p);
		}
	}
	
	private void fillParteLineaBieBomba(List<ParteLineaFb> lin, List<TParteLinea> tpl) {
		for(TParteLinea l : tpl) {
			ParteLineaFb p = new ParteLineaFb();
			
			p.setOrdenBomba(this.toStringField(l.getOrdenBomba()));
			p.setTipoBomba(this.toStringField(l.getTipoBomba().getTipo()));
			p.setMarcaBomba(this.toStringField(l.getMarcaBomba()));
			p.setModeloBomba(this.toStringField(l.getModeloBomba()));
			p.setFechaBomba(l.getFechaBomba());
			p.setMotorBomba(this.toStringField(l.getMotorBomba()));
			p.setVoltajeBomba(this.toStringField(l.getVoltajeBomba()));
			p.setRpmBomba(this.toStringField(l.getRpmBomba()));
			p.setManometroBomba(this.toStringField(l.getManometroBomba()));
			p.setEsferaBomba(this.toStringField(l.getEsferaBomba()));
			p.setValvulasBomba(this.toStringField(l.getValvulasBomba()));
			p.setSaltosBomba(this.toStringField(l.getSaltosBomba()));
			p.setFusiblesBomba(this.toStringField(l.getFusiblesBomba()));
			p.setAlarmaBomba(this.toStringField(l.getAlarmaBomba()));
			p.setCaudalimetroBomba(this.toStringField(l.getCaudalimetroBomba()));
			p.setPresionBomba(this.toStringField(l.getPresionBomba()));
			
			lin.add(p);
		}
	}
	
	private String booleanToString(Boolean b) {
		return b ? "Si" : "No";
	}
	
	private <T> String toStringField(T foo) {
		String res = "";
		
		if(foo instanceof Boolean) {
			res = this.booleanToString((Boolean)foo);
		} else if(foo != null) {
			res = this.checkAndFormatDouble(foo);
		}
		
		return res;
	}
	
	private <T> String checkAndFormatDouble(T x) {
		String res = "";
		
		if(x instanceof Double) {
			DecimalFormat df = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
			res = df.format((Double)x);
		} else {
			res = x.toString();
		}
		
		return res;
	}
	
	private String getJasperFromTipo(Integer oidTipo) {
		String res = "";
		
		switch (oidTipo) {
		case 1:
			res = "parte1";
			break;
		case 2:
			res = "parte2";
			break;
		case 4:
			res = "parte3";
			break;
		default:
			break;
		}
		
		return res;
	}
	
	public File getPdfFolder() 
	{
		return this.parteFolder;
	}

}
