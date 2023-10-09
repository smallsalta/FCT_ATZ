package com.atz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.enums.ComboLongBIE;
import com.atz.enums.ComboSiNo;
import com.atz.enums.ComboTrueFalse;
import com.atz.fb.ContratosBuscarFb;
import com.atz.fb.ContratosFb;
import com.atz.pdf.Descargar;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TEstadoParte;
import com.atz.persistencia.TLineaContrato;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteLinea;
import com.atz.persistencia.TUsuario;
import com.atz.service.ClienteService;
import com.atz.service.ContratoService;
import com.atz.service.EstadoParteService;
import com.atz.service.ParteCambioService;
import com.atz.service.PdfContratoService;
import com.atz.service.PreguntaService;
import com.atz.service.SendMailService;
import com.atz.service.TipoBieService;
import com.atz.service.TipoExtintorService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Contrato 
{
	@Autowired
	private ContratoService kservice;
	
	@Autowired
	private ClienteService cservice;
	
	@Autowired
	private PdfContratoService pservice;
	
	@Autowired
	private PreguntaService qservice;
	
	@Autowired
	private SendMailService smservice;
	
	@Autowired
	private TipoExtintorService teservice;
	
	@Autowired
	private TipoBieService tbservice;
	
	@Autowired
	private EstadoParteService epservice;
	
	@Autowired
	private ParteCambioService pcservice;
	
	@Autowired
	@Qualifier("pdfFolder")
	private File pdfFolder;
	
	private Logger log = LogManager.getLogger( Contrato.class );
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
	} 
	
	@RequestMapping("contrato_buscar.do")
	public ModelAndView buscar(HttpSession s) 
	{
		ModelMap m 	= new ModelMap( "clientes", this.cservice.leerTodos() );
		
		return new ModelAndView("contrato_buscar", m);
	}
	
	@RequestMapping("contrato_buscar_listado.do")
	public ModelAndView cargar(ContratosBuscarFb fb) 
	{
		TCliente c 				= this.cservice.leer( fb.getOidcliente() );
		List<TContrato> cont	= this.kservice.leerContratosCliente( fb.getOidcliente() );
		ModelMap m 				= new ModelMap();
		
		m.put("contratos", cont);
		m.put("cliente", c);
		
		return new ModelAndView("contrato_buscar_listado", m);
	}
	
	@RequestMapping("contrato_crear.do")
	public ModelAndView crear(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		
		return new ModelAndView( "contrato_crear", m);
	}
	
	@RequestMapping("contrato_crear_filas.do")
	public ModelAndView crearFilas(HttpSession s) 
	{
		ModelMap m 			= new ModelMap();
		Integer n			= this.kservice.maxNumero();
		TContrato c			= new TContrato();
		TLineaContrato lc	= new TLineaContrato();
		Date ahora			= new Date();
		
		c.setNumero(n);
		c.setFecha(ahora);
		
		lc.setFechaFab(ahora);
		
		c.getTLineaContratos().add(lc);
		
		m.put( "contrato", c );
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "agentes", this.kservice.leerAgentes() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		
		return new ModelAndView( "contrato_crear_filas", m );
	}
	
	@RequestMapping("contrato_crear_preguntas.do")
	public ModelAndView crearPreguntas() 
	{
		return new ModelAndView( "contrato_crear_preguntas" );
	}
	
	@RequestMapping("contrato_formulario.do")
	public ModelAndView contratoFormulario() 
	{
		return new ModelAndView("contrato_formulario");
	}
	
	@RequestMapping("contrato_crear_guardar.do")
	public ModelAndView crearGuardar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Integer n				= this.kservice.maxNumero();
		
		fb.setNumero(n);
		
		TContrato c				= this.kservice.crear(fb);
		ContratosBuscarFb cb 	= new ContratosBuscarFb();
				
		cb.setOid( c.getOid() );
		
		return this.buscar(cb, s);
	}
	
	@RequestMapping("contrato_buscar_cargar.do")
	public ModelAndView buscar(ContratosBuscarFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TContrato c = this.kservice.leer( fb.getOid() );
		
		return this.cargarContrato(c, s, null);
	}
	
	@RequestMapping("contrato_buscar_guardar.do")
	public ModelAndView buscarGuardar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TContrato c = this.kservice.actualizar(fb);
		
		return this.cargarContrato(c, s, null);
	}
	
	private ModelAndView cargarContrato(TContrato c, HttpSession s, String falloPase)
	{
		ModelMap m		= new ModelMap();
		Set<Integer> o 	= c.getTLineaContratos().stream().map( t -> t.getTAgente().getOid() ).collect( Collectors.toSet() );
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "agentes", this.kservice.leerAgentes() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		m.put( "contrato", c );
		m.put( "preguntas", this.qservice.getPreguntas(o) );
		m.put( "respuestas", this.qservice.getRespuestas() );
		
		if(falloPase != null) {
			m.put("errorPase", falloPase);
		}
		
		return new ModelAndView("contrato_buscar_cargar", m);
	}
	
	@RequestMapping("contrato_buscar_cargar_filas.do")
	public ModelAndView buscarFilas() 
	{
		return new ModelAndView("contrato_buscar_cargar_filas");
	}
	
	@RequestMapping("contrato_buscar_cargar_preguntas.do")
	public ModelAndView buscarPreguntas() 
	{
		return new ModelAndView("contrato_buscar_cargar_preguntas");
	}
	
	@RequestMapping("contrato_borrar.do")
	public ModelAndView borrar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.kservice.borrar( fb.getOid() );
		
		return this.buscar(s);
	}
	
	@RequestMapping("contrato_pdf.do")
	public void pdf(ContratosBuscarFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		File pdfFile		= this.pservice.crear(fb);
		Descargar desc		= new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("contrato_cuadrante.do")
	public void cuadrante(HttpServletResponse resp) 
	throws IOException 
	{
		File cuadrante 		= new File( this.pdfFolder.getAbsolutePath() + "/cuadrante.pdf" );
		Descargar desc		= new Descargar();
		
		desc.flush(cuadrante, resp);
	}
	
	@RequestMapping("contrato_email.do")
	public void email(ContratosBuscarFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		File pdfFile	= this.pservice.crear(fb);	
		TCliente c 		= this.cservice.leer( fb.getOidcliente() );
		String mensaje	= "Mensaje enviado";
		PrintWriter out = resp.getWriter();
        
		try 
		{
			this.smservice.enviarConCCyCuadrante(c, pdfFile);
		} 
		catch(MessagingException e)
		{
			this.log.info("Ups", e);
			
			mensaje		= "Error al enviar el mensaje";
		}
		
		this.kservice.actualizaAuditoriaEmail(fb.getOid());
		
		out.println(mensaje);
		out.close();
	}
	
	@RequestMapping("contrato_copia.do")
	public ModelAndView copia(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		fb.setNumero( this.kservice.maxNumero() );
		fb.setOid(null);
		
		return this.crearGuardar(fb, s);
	}
	
	@RequestMapping("contrato_parte.do")
	public ModelAndView contratoParte(ContratosFb fb, HttpSession s, BindingResult result) 
	throws IllegalAccessException, InvocationTargetException 
	{
		try 
		{
			TUsuario u	= (TUsuario) s.getAttribute("usuario");
			fb.setOidusuario(u.getOid());
			
			TParte p 	= this.kservice.crearParte(fb);
			return this.cargarParte(p, s);
		} 
		catch(ConstraintViolationException e) 
		{
			TContrato c = this.kservice.leer( fb.getOid() );
			
			return this.cargarContrato(c, s, "No se puede crear el parte. Asegurese de que todas las filas corresponden a partes BIE, partes Extintor o partes Centralita");
		}
		
	}
	
	private ModelAndView cargarParte(TParte c, HttpSession s) {
		
		ModelMap m			= new ModelMap();
		
		Set<Integer> o  	= this.getPreguntas(c);
		
		int oidpartetipo 	= c.getTParteTipo().getOid();
		List<TEstadoParte> l = this.epservice.leerTodos();
		
		m.put( "estadosparte", l);
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "parte", c );
		m.put( "oidpartetipo", oidpartetipo);
		
		m.put( "tiposextintor", this.getTiposExtintor(oidpartetipo));
		m.put( "preguntas", this.qservice.getPreguntas(o));
		m.put( "respuestas", this.qservice.getRespuestas() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		m.put( "historial", this.pcservice.leerTodos( c.getOid() ) );
		
		if(oidpartetipo != 3) {
			m.put( "tiposextintor", this.getTiposExtintor(oidpartetipo));
			
			List<ComboSiNo> comboSiNo = new ArrayList<>();
			comboSiNo.add(ComboSiNo.NO);
			comboSiNo.add(ComboSiNo.SI);
			
			
			List<ComboTrueFalse> comboTrueFalse = new ArrayList<>();
			comboTrueFalse.add(ComboTrueFalse.NO);
			comboTrueFalse.add(ComboTrueFalse.SI);
			
			List<ComboLongBIE> comboLongBie = new ArrayList<>();
			comboLongBie.addAll(Arrays.asList(ComboLongBIE.values()));
				
			
			m.put("combosino", comboSiNo);
			m.put("combotruefalse", comboTrueFalse);
			m.put("combolongbie", comboLongBie);
		} else {
			m.put("lineascentral", c.getTParteLineas().stream().filter(x -> x.getOrdenCentral() != null).collect(Collectors.toList()));
			m.put("lineasfuente", c.getTParteLineas().stream().filter(x -> x.getOrdenFuente() != null).collect(Collectors.toList()));
			m.put("lineasdetectores", c.getTParteLineas().stream().filter(x -> x.getOrdenDetectores() != null).collect(Collectors.toList()));
			m.put("lineaspulsadores", c.getTParteLineas().stream().filter(x -> x.getOrdenPulsadores() != null).collect(Collectors.toList()));
			m.put("lineassirenas", c.getTParteLineas().stream().filter(x -> x.getOrdenSirenas() != null).collect(Collectors.toList()));
		}
		
		return new ModelAndView("parte_buscar_cargar", m);
	}
	
	private Set<Integer> getPreguntas(TParte p) {
		Set<Integer> res = new HashSet<>();
		
		if(p.getTParteTipo().getDescripcion().equals("BIE")) {
			res = p.getTParteLineas().stream().map(x -> this.getAgenteBie(x)).collect(Collectors.toSet());
		} else if(p.getTParteTipo().getDescripcion().equals("Extintores")) {
			res = p.getTParteLineas().stream().map(x -> this.getAgenteExtintor(x)).collect(Collectors.toSet());
		}
		
		return res;
	}
	
	private Integer getAgenteExtintor(TParteLinea l) {
		
		Integer res = 0;
		
		if(l.getTipo().getTipo().equals("ABC")) {
			res = 1;
		} else if(l.getTipo().getTipo().equals("CO2")) {
			res = 2;
		}
		
		return res;
	}
	
	private Integer getAgenteBie(TParteLinea l) {
		
		Integer res = 0;
		
		if(l.getLongMang().equals("25/20")) {
			res =  3002;
		} else if(l.getLongMang().equals("45/20")) {
			res = 3004;
		}
		
		return res;
	}
	
	private List<?> getTiposExtintor(int oidpartetipo) {
		if(oidpartetipo == 1) {
			return this.teservice.leerTodos();
		} else {
			return this.tbservice.leerTodos();
		}
	}
}
