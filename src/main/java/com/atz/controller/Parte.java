package com.atz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.comparator.TMatrimonioComparator;
import com.atz.enums.ComboLongBIE;
import com.atz.enums.ComboSiNo;
import com.atz.enums.ComboTrueFalse;
import com.atz.fb.ContratosBuscarFb;
import com.atz.fb.ParteBuscarFb;
import com.atz.fb.PartesFb;
import com.atz.pdf.Descargar;
import com.atz.persistencia.TAgente;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TEstadoParte;
import com.atz.persistencia.TFactura;
import com.atz.persistencia.TMatrimonio;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteLinea;
import com.atz.persistencia.TUsuario;
import com.atz.service.AgenteService;
import com.atz.service.ClienteService;
import com.atz.service.ContratoService;
import com.atz.service.EmpresaService;
import com.atz.service.EstadoParteService;
import com.atz.service.EstadosService;
import com.atz.service.FacturaService;
import com.atz.service.MatrimonioService;
import com.atz.service.ParteCambioService;
import com.atz.service.ParteService;
import com.atz.service.PdfContratoService;
import com.atz.service.PdfFacturaService;
import com.atz.service.PdfParteService;
import com.atz.service.PreguntaService;
import com.atz.service.SelectCentralitaService;
import com.atz.service.SendMailService;
import com.atz.service.TipoBieService;
import com.atz.service.TipoExtintorService;
import com.atz.service.UsuarioService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Parte 
{
	@Autowired
	private UsuarioService uservice;
	
	@Autowired
	private ClienteService cservice;
	
	@Autowired
	private FacturaService fservice;
	
	@Autowired
	private ParteService pservice;
	
//	@Autowired
//	private PdfParteService pdfservice;
	
	@Autowired
	private PdfParteService pdfParte;
	
	@Autowired
	private SendMailService smservice;
	
	@Autowired
	private TipoExtintorService teservice;
	
	@Autowired
	private TipoBieService tbservice;
	
	@Autowired
	private EstadoParteService epservice;
	
	@Autowired
	private PreguntaService qservice;
	
	@Autowired
	private ParteCambioService pcservice;
	
	@Autowired
	private ContratoService kservice;
	
	@Autowired
	private MatrimonioService mservice;
	
	@Autowired
	private SelectCentralitaService selectService;
	
	@Autowired
	private PdfContratoService pdfContrato;
	
	@Autowired
	private PdfFacturaService pdfFactura;
	
	@Autowired
	private EmpresaService eservice;
	
	@Autowired
	private EstadosService stservice;
	
	@Autowired
	private AgenteService agservice;

	private Logger log = LogManager.getLogger( Parte.class );
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
	} 
	
	@RequestMapping("parte_buscar.do")
	public ModelAndView buscar() 
	{
		ModelMap m 				= new ModelMap();
		Date d 					= new Date();
		List<TEstadoParte> l 	= this.epservice.leerTodos();
		
		m.put( "estadosparte", l);
		m.put( "usuarios", this.uservice.getTodos() );
		m.put( "clientes" , this.cservice.leerTodos() );
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView("parte_buscar", m);
	}
	
	@RequestMapping("parte_buscar_listado.do")
	public ModelAndView cargar(ParteBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.log.info(fb);
		
		ModelMap m						= new ModelMap();
		List<TParte> lp 				= this.pservice.leerPartesBuscar(fb);
		List<TMatrimonio> lm 			= this.mservice.leer(lp);
		Map<String, Boolean> me			= this.fservice.leerFacturasEstado(lm);
		Map<Integer, TMatrimonio> mp	= lm.stream().collect( Collectors.toMap( TMatrimonio::getParte, t -> t ) );
		
		m.put("partes", lp );
		m.put("matrimonio", mp );
		m.put("pagadas", me );
		
		return new ModelAndView("parte_buscar_listado", m);
	}
	
	@RequestMapping("parte_crear.do")
	public ModelAndView crear(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		m.put("oidpartetipo", 1);

		m.put( "tiposextintor", this.teservice.leerTodos());
		m.put( "estados", this.stservice.getTodos() );
		
		List<ComboSiNo> comboSiNo = new ArrayList<>();
		comboSiNo.add(ComboSiNo.NO);
		comboSiNo.add(ComboSiNo.SI);
		comboSiNo.add(ComboSiNo.NC);
		
		List<ComboTrueFalse> comboTrueFalse = new ArrayList<>();
		comboTrueFalse.add(ComboTrueFalse.NO);
		comboTrueFalse.add(ComboTrueFalse.SI);
		
		List<ComboLongBIE> comboLongBie = new ArrayList<>();
		comboLongBie.addAll(Arrays.asList(ComboLongBIE.values()));
			
		m.put("combosino", comboSiNo);
		m.put("combotruefalse", comboTrueFalse);
		m.put("combolongbie", comboLongBie);
		m.put("pruebas", this.kservice.leerPruebas());
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_bie.do")
	public ModelAndView crearBie(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		
		m.put("oidpartetipo", 2);
		m.put("tiposextintor", this.tbservice.leerTodos());
		m.put( "estados", this.stservice.getTodos() );
		
		List<ComboSiNo> comboSiNo = new ArrayList<>();
		comboSiNo.add(ComboSiNo.NO);
		comboSiNo.add(ComboSiNo.SI);
		comboSiNo.add(ComboSiNo.NC);
		
		List<ComboTrueFalse> comboTrueFalse = new ArrayList<>();
		comboTrueFalse.add(ComboTrueFalse.NO);
		comboTrueFalse.add(ComboTrueFalse.SI);
		
		List<ComboLongBIE> comboLongBie = new ArrayList<>();
		comboLongBie.addAll(Arrays.asList(ComboLongBIE.values()));
			
		m.put("combosino", comboSiNo);
		m.put("combotruefalse", comboTrueFalse);
		m.put("combolongbie", comboLongBie);
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_observaciones.do")
	public ModelAndView crearObservaciones(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		m.put("oidpartetipo", 4);
		m.put( "estados", this.stservice.getTodos() );
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_centralita.do")
	public ModelAndView crearCentralita(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		m.put("oidpartetipo", 3);
		
		m.put("tiposequipoauxiliar", this.selectService.getAllTipoEquipoAuxiliar());
		m.put("estadolineacentralita", this.selectService.getAllEstadoLineaCentralita());
		m.put("tiposirenas", this.selectService.getAllTipoSirenas());
		m.put("tipodetectores", this.selectService.getAllTipoDetectores());
		m.put("funciondetectores", this.selectService.getAllFuncionDetectores());
		m.put("tipopulsadores", this.selectService.getAllTipoPulsadores());
		m.put("tipocentral", this.selectService.getAllTipoCentral());
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_centralita_central.do")
	public ModelAndView crearCentralitaCentral(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		m.put("oidpartetipo", 5);
		m.put( "estados", this.stservice.getTodos() );
		m.put("tiposequipoauxiliar", this.selectService.getAllTipoEquipoAuxiliar());
		m.put("estadolineacentralita", this.selectService.getAllEstadoLineaCentralita());
		m.put("tiposirenas", this.selectService.getAllTipoSirenas());
		m.put("tipodetectores", this.selectService.getAllTipoDetectores());
		m.put("funciondetectores", this.selectService.getAllFuncionDetectores());
		m.put("tipopulsadores", this.selectService.getAllTipoPulsadores());
		m.put("tipocentral", this.selectService.getAllTipoCentral());
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_centralita_auxiliares.do")
	public ModelAndView crearCentralitaAuxiliares(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		m.put("oidpartetipo", 6);
		m.put( "estados", this.stservice.getTodos() );
		m.put("tiposequipoauxiliar", this.selectService.getAllTipoEquipoAuxiliar());
		m.put("estadolineacentralita", this.selectService.getAllEstadoLineaCentralita());
		m.put("tiposirenas", this.selectService.getAllTipoSirenas());
		m.put("tipodetectores", this.selectService.getAllTipoDetectores());
		m.put("funciondetectores", this.selectService.getAllFuncionDetectores());
		m.put("tipopulsadores", this.selectService.getAllTipoPulsadores());
		m.put("tipocentral", this.selectService.getAllTipoCentral());
		
		return new ModelAndView( "parte_crear", m);
	}
	
	@RequestMapping("parte_crear_guardar.do")
	public ModelAndView crearGuardar(PartesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Integer n	= this.pservice.maxNumero();
		TUsuario u	= (TUsuario) s.getAttribute("usuario");	
		
		fb.setNumero(n);
		fb.setOidusuario( u.getOid() );
		
		TParte c				= this.pservice.crear(fb);
		ContratosBuscarFb cb 	= new ContratosBuscarFb();
				
		cb.setOid( c.getOid() );
		
		return this.buscar(cb);
	}
	
	@RequestMapping("parte_buscar_cargar.do")
	public ModelAndView buscar(ContratosBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TParte c = this.pservice.leer( fb.getOid() );
		
		return this.cargarParte(c);
	}
	
	private List<?> getTiposExtintor(int oidpartetipo) {
		if(oidpartetipo == 1) {
			return this.teservice.leerTodos();
		} else {
			return this.tbservice.leerTodos();
		}
	}

	private ModelAndView cargarParte(TParte c) 
	throws IllegalAccessException, InvocationTargetException
	{
		ModelMap m		= new ModelMap();
		
		Set<Integer> o  = this.getPreguntas(c);
		
		int oidpartetipo = c.getTParteTipo().getOid();
		List<TEstadoParte> l = this.epservice.leerTodos();
		
		m.put( "estadosparte", l);
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "parte", c );
		m.put( "oidpartetipo", oidpartetipo);
		m.put( "estados", this.stservice.getTodos() );
		
		m.put( "preguntas", this.qservice.getPreguntas(o));
		m.put( "respuestas", this.qservice.getRespuestas() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		m.put( "historial", this.pcservice.leerTodos( c.getOid() ) );
		m.put( "partechapuza", this.pservice.isPaseChapuza(c));
		
		if(oidpartetipo == 1 || oidpartetipo == 2 ||oidpartetipo == 4) {
			m.put( "tiposextintor", this.getTiposExtintor(oidpartetipo));
			
			List<ComboSiNo> comboSiNo = new ArrayList<>();
			comboSiNo.add(ComboSiNo.NO);
			comboSiNo.add(ComboSiNo.SI);
			comboSiNo.add(ComboSiNo.NC);
			
			
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
			m.put("lineasequipoauxiliar", c.getTParteLineas().stream().filter(x -> x.getOrdenEquipoAuxiliar() != null).collect(Collectors.toList()));
			m.put("lineasretenedor", c.getTParteLineas().stream().filter(x -> x.getOrdenRetenedor() != null).collect(Collectors.toList()));
			m.put("lineaspuertas", c.getTParteLineas().stream().filter(x -> x.getOrdenPuertas() != null).collect(Collectors.toList()));
			
			m.put("tiposequipoauxiliar", this.selectService.getAllTipoEquipoAuxiliar());
			m.put("estadolineacentralita", this.selectService.getAllEstadoLineaCentralita());
			m.put("tiposirenas", this.selectService.getAllTipoSirenas());
			m.put("tipodetectores", this.selectService.getAllTipoDetectores());
			m.put("funciondetectores", this.selectService.getAllFuncionDetectores());
			m.put("tipopulsadores", this.selectService.getAllTipoPulsadores());
			m.put("tipocentral", this.selectService.getAllTipoCentral());
		}
		
		return new ModelAndView("parte_buscar_cargar", m);
	}
	
	private Set<Integer> getPreguntas(TParte p) {
		Set<Integer> res = new HashSet<>();

		
		switch(p.getTParteTipo().getOid()) {
		
			case 1:
				res = p.getTParteLineas().stream().map(x -> this.getAgente(x.getTipo().getTipo())).collect(Collectors.toSet());
				break;
			case 2:
				res = p.getTParteLineas().stream().map(x -> this.getAgente(x.getLongMang())).collect(Collectors.toSet());
				break;
			case 5:
				res = p.getTParteLineas().stream()
				.filter(x -> (x.getOrdenCentral() != null && x.getOrdenCentral() > 0) 
						|| (x.getOrdenFuente() != null && x.getOrdenFuente() > 0) 
						|| (x.getOrdenDetectores() != null && x.getOrdenDetectores() > 0)
						|| (x.getOrdenPulsadores() != null && x.getOrdenPulsadores() > 0)
						|| (x.getOrdenSirenas() != null && x.getOrdenSirenas() > 0))
				.map(x -> this.getAgenteCentralita(x))
				.collect(Collectors.toSet());
				break;
			case 6:
				res = p.getTParteLineas().stream()
				.filter(x -> (x.getOrdenEquipoAuxiliar() != null && x.getOrdenEquipoAuxiliar() > 0) 
						|| (x.getOrdenRetenedor() != null && x.getOrdenRetenedor() > 0) 
						|| (x.getOrdenPuertas() != null && x.getOrdenPuertas() > 0))
				.map(x -> this.getAgenteCentralita(x))
				.collect(Collectors.toSet());
				break;
		}
		
		return res;
	}
	
	private Integer getAgenteCentralita(TParteLinea l) {
		Integer res = 0;
		
		if(l.getOrdenCentral() != null) {
			res = 3010; // res = 3005
		} else if(l.getOrdenDetectores() != null) {
			res = 5;
		} else if(l.getOrdenPulsadores() != null) {
			res = 4;
		} else if(l.getOrdenSirenas() != null) {
			res = 3001;
		} else if(l.getOrdenEquipoAuxiliar() != null) {
			res = 3007;
		}
		
		return res;
	}
	
	
	private Integer getAgente(String desc) {
		Integer res = 0;
		
		TAgente a = this.agservice.getByDecr(desc);
		if(a != null) {
			res = a.getOid();
		}
		
		return res;
	}
	
	@RequestMapping("parte_buscar_guardar.do")
	public ModelAndView buscarGuardar(PartesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException {

		TUsuario u		= (TUsuario) s.getAttribute("usuario");	
		fb.setOidusuario( u.getOid() );
		
		TParte c = this.pservice.actualizar(fb);
		return this.cargarParte(c);
		
	}
	
	@RequestMapping("parte_crear_filas.do")
	public ModelAndView crearFilas(HttpSession s) 
	{
		ModelMap m 			= new ModelMap();
		TParte p			= new TParte();
		TParteLinea lp		= new TParteLinea();
		Date d				= new Date();
		
		p.setFecha(d);
		p.setNumero( this.pservice.maxNumero() );
		
		//lp.setOrden(null);
		lp.setFechaFabricacion(d);
		//lp.setUltimoRetimbrado(d);
		lp.setFechaRetimA(d);
		//lp.setFechaRetimB(d);
		lp.setCartel(d);
		
		p.getTParteLineas().add(lp);
		
		m.put( "parte", p );
		m.put( "clientes", this.cservice.leerTodos() );
		
		List<TEstadoParte> l = this.epservice.leerTodos();
		
		m.put( "estadosparte", l);
		
		return new ModelAndView( "parte_crear_filas", m );
	}
	
	@RequestMapping("parte_buscar_cargar_preguntas.do")
	public ModelAndView buscarPreguntas() 
	{
		return new ModelAndView("parte_buscar_cargar_preguntas");
	}
	
	@RequestMapping("parte_buscar_cargar_filas.do")
	public ModelAndView buscarFilas() {
		return new ModelAndView("parte_buscar_cargar_filas");
	}
	
	@RequestMapping("parte_buscar_cargar_historial.do")
	public ModelAndView buscarHistorial() 
	{
		return new ModelAndView("parte_buscar_cargar_historial");
	}
	
	@RequestMapping("parte_borrar.do")
	public ModelAndView borrar(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.pservice.borrar( fb.getOid() );
		
		return this.buscar();
	}
	
	@RequestMapping("parte_copia.do")
	public ModelAndView copia(PartesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException {
		fb.setNumero(this.pservice.maxNumero());
		fb.setOid(null);
		
		return this.crearGuardar(fb, s);
	}
	
	@RequestMapping("parte_pdf.do")
	public void pdf(PartesFb fb, HttpServletResponse resp)
	throws IllegalAccessException, InvocationTargetException, JRException, IOException {
		File pdfFile = this.pdfParte.crear(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_pdf_1.do")
	public void pdf1(PartesFb fb, HttpServletResponse resp)
	throws IllegalAccessException, InvocationTargetException, JRException, IOException {
		File pdfFile = this.pdfParte.crear1(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_pdf_2.do")
	public void pdf2(PartesFb fb, HttpServletResponse resp)
	throws IllegalAccessException, InvocationTargetException, JRException, IOException {
		File pdfFile = this.pdfParte.crear2(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_chapuza.do")
	public void parteChapuza(PartesFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException, ParseException {
		File pdfFile = this.pdfParte.chapuza(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_chapuza1.do")
	public void parteChapuza1(PartesFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException, ParseException {
		File pdfFile = this.pdfParte.chapuza1(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_chapuza2.do")
	public void parteChapuza2(PartesFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException, ParseException {
		File pdfFile = this.pdfParte.chapuza2(fb);
		Descargar desc = new Descargar();
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("parte_email.do")
	public void email(PartesFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		// File pdfFile 	= this.pdfservice.crear(fb);
		File pdfFile = new File("");
	
		switch (fb.getOidpartetipo()) 
		{
		case 5:
			pdfFile = this.pdfParte.crear1(fb);
			break;
		case 6:
			pdfFile = this.pdfParte.crear2(fb);
			break;
		default:
			pdfFile = this.pdfParte.crear(fb);
			break;
		}
		
		TCliente c 		= this.cservice.leer( fb.getOidcliente() );
		String mensaje	= "Mensaje enviado";
		PrintWriter out = resp.getWriter();
		
		try 
		{
			this.smservice.enviarSinCC( c, fb.getCcemail(), pdfFile );
		} 
		catch(MessagingException e)
		{
			this.log.info("Ups", e);
			
			mensaje		= "Error al enviar el mensaje";
		}
		
		this.pservice.actualizaAuditoriaEmail(fb.getOid());
		
		out.println(mensaje);
		out.close();
	}
	
	@RequestMapping("parte_contrato.do")
	public ModelAndView parteContrato(PartesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		// Creamos el contrato ...
		TContrato c			= this.pservice.crearContrato(fb);
		
		// Creamos el matrimonio ...
		ParteBuscarFb pb	= new ParteBuscarFb();
		TMatrimonio tm		= this.mservice.obtenerPorOid( fb.getNumero() );
		
		pb.setNparte( fb.getNumero() );
		pb.setNcontrato( c.getNumero() );
		pb.setNfactura( tm.getFactura() );
		pb.setOidmatrimonio( tm.getOid() );
		
 		this.mservice.guardar( pb, tm.getNumero2() );
		
 		// Cargamos el contrato ...
		return this.cargarContrato(c, s);
	}
	
	@RequestMapping("parte_factura_sl.do")
	public ModelAndView parteFacturaSl(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		// Creamos la factura ...
		TFactura fct = this.fservice.crearSl(fb);
		
		return this.parteFactura(fct, fb);
	}
	
	@RequestMapping("parte_factura_carpinteria.do")
	public ModelAndView parteFacturaCarpinteria(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		// Creamos la factura ...
		TFactura fct = this.fservice.crearCarpinteria(fb);
		
		return this.parteFactura(fct, fb);
	}
	
	private ModelAndView parteFactura(TFactura fct, PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		// Creamos el matrimonio ...
		ParteBuscarFb pb	= new ParteBuscarFb();
		TMatrimonio tm		= this.mservice.obtenerPorOid( fb.getNumero() );
		
		pb.setNparte( fb.getNumero() );
		pb.setNfactura( fct.getNumero() );
		pb.setNcontrato( tm.getContrato() );
		pb.setOidmatrimonio( tm.getOid() );
		
 		this.mservice.guardar( pb, fct.getNumero2() );
		
 		// Navegamos a la factura ...
 		ModelMap m = new ModelMap();
 		
 		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "empresas", this.eservice.leerTodas() );
		m.put( "factura", fct );
		m.put( "estados", this.stservice.getTodos() );
		
		return new ModelAndView("factura_buscar_cargar", m);
	}
	
	@RequestMapping("parte_buscar_matrimonio.do")
	public ModelAndView parteBuscarMatrimonio() 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.log.info("");
		
		ModelAndView mav = this.buscar();
		
		mav.setViewName("parte_buscar_matrimonio");
		
		return mav;
	}	
	
	@RequestMapping("parte_listado_matrimonio.do")
	public ModelAndView parteListadoMatrimonio(ParteBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.log.info(fb);
		
		ModelMap m				= new ModelMap();
		
		List<TParte> lp 		= this.pservice.leerPartesBuscar(fb);
		Map<Integer, TParte> mp = lp.stream().collect( Collectors.toMap( TParte::getNumero, t -> t ) );
		List<TMatrimonio> lm 	= this.mservice.leer(lp);
		List<TMatrimonio> lmf	= this.fusionParteMatrimonio(lm, lp);
		
		Collections.sort( lmf, new TMatrimonioComparator() );	
		
		m.put( "matrimonio", lmf );
		m.put( "partes", mp );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		
		return new ModelAndView("parte_listado_matrimonio", m);
	}
	
	/**
	 * Para cada Parte se obtiene un Matrimonio.
	 * O bien el que hay en base de datos o bien uno nuevo con datos básicos
	 * @param lm	Lista de Matrimonios en base de datos
	 * @param lp	Lista de Partes
	 * @return		Lista de Matrimonios existentes en base de datos y nuevos
	 */
	private List<TMatrimonio> fusionParteMatrimonio(List<TMatrimonio> lm, List<TParte> lp)
	{
		 Map<Integer, TMatrimonio> buscador = lm.stream().collect( Collectors.toMap( t -> t.getParte(), t -> t ) );
		 List<TMatrimonio> lmf 				= new LinkedList<>();
		 
		 lp.forEach
		 	( 
		 		p ->
		 			{
		 				lmf.add
		 				( 
		 					buscador.getOrDefault
		 					( 
		 						p.getNumero(), 
		 						new TMatrimonio( 0, p.getNumero(), 0, 0 ) 
		 					)
		 				);
		 			}
		 	);

		 
		 return lmf;
	}
	
	@RequestMapping("parte_guardar_matrimonio.do")
	public ModelAndView parteGuardarMatrimonio(ParteBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.log.info(fb);
		
		Integer oid = fb.getOidmatrimonio(); 
		String n2	= null;
		
		if( oid != null && oid != 0 )
		{
			TMatrimonio tm 	= this.mservice.obtenerPorOid(oid);
			n2 				= tm.getNumero2();
		}
		
		TMatrimonio tm 		= this.mservice.guardar(fb, n2);
		ModelAndView mav 	= this.parteListadoMatrimonio(fb);
		
		mav.getModel().put( "exito", tm != null );
		
		return mav;
	}	
	
	@RequestMapping("parte_borrar_matrimonio.do")
	public ModelAndView parteBorrarMatrimonio(ParteBuscarFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.log.info(fb);
		
		this.mservice.borrar(fb);
		
		return this.parteListadoMatrimonio(fb);
	}	
	
	@RequestMapping("parte_matrimonio_lupa.do")
	public void parteMatrimonioLupa(ParteBuscarFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, IOException 
	{
		this.log.info(fb);
		
		String uri 		= null;
		File f 			= null; 
		Descargar desc	= new Descargar();
		
		switch( fb.getTipoLupa() )
		{
			case "parte": 
				uri = this.pdfParte.getPdfFolder().getAbsolutePath() + "/" + fb.getNparte() + ".pdf";
				break;
				
			case "factura": 
				uri = this.pdfFactura.getPdfFolder().getAbsolutePath() + "/" + fb.getN2factura() + ".pdf";
				break;
				
			case "contrato": 
				uri = this.pdfContrato.getPdfFolder().getAbsolutePath() + "/" + fb.getNcontrato() + ".pdf";
				break;
				
			case "mail": 
				TParte op = this.pservice.leer( fb.getNparte() );
				
				// Creamos y enviamos la factura ...
				try 
				{
					
					TFactura of	= this.fservice.leerN2( fb.getN2factura() );
					
					this.pdfFactura.crear(of);	
					this.smservice.enviarSinCC( op.getTCliente(), "", f );
					this.fservice.actualizarFechaEnvio( of.getOid() );
					
					uri	= this.pdfFactura.getPdfFolder().getAbsolutePath() + "/" + fb.getN2factura() + ".pdf";
				} 
				catch(Exception e)
				{
					this.log.info("Ups", e);
				}
				
				// Creamos y enviamos el contrato ...
				try 
				{
					TContrato oc = this.kservice.leer2( fb.getNcontrato() );
					
					this.pdfContrato.crear(oc);
					this.smservice.enviarConCCyCuadrante( op.getTCliente(), "", f );
					this.kservice.actualizaAuditoriaEmail( oc.getOid() );
					
					uri	= this.pdfContrato.getPdfFolder().getAbsolutePath() + "/" + fb.getNcontrato() + ".pdf";
				} 
				catch(Exception e)
				{
					this.log.info("Ups", e);
				}
				
				break;	
		}
		
		this.log.info(uri);
		
		f = new File(uri);

		if( !f.exists() )
		{
			uri	= this.pdfFactura.getPdfFolder().getAbsolutePath() + "/error.pdf";
			f	= new File(uri);
		}
		
		desc.flush( f, resp );
	}	
		
	private ModelAndView cargarContrato(TContrato c, HttpSession s)
	{
		ModelMap m		= new ModelMap();
		Set<Integer> o 	= c.getTLineaContratos().stream().map( t -> t.getTAgente().getOid() ).collect( Collectors.toSet() );
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "agentes", this.kservice.leerAgentes() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		m.put( "contrato", c );
		m.put( "preguntas", this.qservice.getPreguntas(o) );
		m.put( "respuestas", this.qservice.getRespuestas() );
		
		return new ModelAndView("contrato_buscar_cargar", m);
	}
	
	private ModelAndView cargarFactura(TContrato c, HttpSession s)
	{
		ModelMap m		= new ModelMap();
		Set<Integer> o 	= c.getTLineaContratos().stream().map( t -> t.getTAgente().getOid() ).collect( Collectors.toSet() );
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "agentes", this.kservice.leerAgentes() );
		m.put( "pruebas", this.kservice.leerPruebas() );
		m.put( "contrato", c );
		m.put( "preguntas", this.qservice.getPreguntas(o) );
		m.put( "respuestas", this.qservice.getRespuestas() );
		
		return new ModelAndView("contrato_buscar_cargar", m);
	}
}
