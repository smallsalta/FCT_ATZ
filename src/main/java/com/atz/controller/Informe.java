package com.atz.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atz.fb.ContratosBuscarFb;
import com.atz.fb.InformesFb;
import com.atz.pdf.Descargar;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TFactura;
import com.atz.persistencia.TMatrimonio;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TUsuario;
import com.atz.service.ClienteService;
import com.atz.service.ContratoService;
import com.atz.service.EmpresaService;
import com.atz.service.FacturaService;
import com.atz.service.MatrimonioService;
import com.atz.service.ParteService;
import com.atz.service.PdfContratoService;
import com.atz.service.PdfFacturaService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Informe 
{
	@Autowired
	private ContratoService cservice;
	
	@Autowired
	private FacturaService fservice;
	
	@Autowired
	private PdfContratoService pservice;
	
	@Autowired
	private PdfFacturaService xservice;
	
	@Autowired
	private EmpresaService eservice;
	
	@Autowired
	private ClienteService clservice;
	
	@Autowired
	private ParteService ptservice;
	
	@Autowired
	private MatrimonioService mservice;
	
	@Autowired
	@Qualifier("pdfFolder")
	private File pdfFolder;
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
	} 
	
	@RequestMapping("informe_contrato.do")
	public ModelAndView contrato() 
	{
		ModelMap m 	= new ModelMap();
		Date d 		= new Date();
		
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView( "informe_contrato", m );
	}
	
	@RequestMapping("informe_contrato_listado.do")
	public ModelAndView listado(InformesFb fb, HttpSession s) 
	{
		TUsuario u					= (TUsuario) s.getAttribute("usuario");
		String rol					= u.getTRol().getDescr();
		Integer oid					= rol.equals("admin") || rol.equals("factura") ? null : u.getOid();
		List<TContrato> contratos 	= this.cservice.leerContratosFechas( fb.getFini(), fb.getFfin(), oid );
		ModelMap m 					= new ModelMap();
		
		m.put( "contratos", contratos );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		
		return new ModelAndView("informe_contrato_listado", m);
	}
	
	@RequestMapping("informe_pdf.do")
	public void pdf(int oid, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		ContratosBuscarFb fb	= new ContratosBuscarFb();
		
		fb.setOid(oid);
		
		Descargar desc			= new Descargar();
		File pdfFile			= this.pservice.leer(fb);
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("informe_factura.do")
	public ModelAndView factura() 
	{
		ModelMap m 	= new ModelMap();
		Date d 		= new Date();		
		
		m.put( "empresas", this.eservice.leerTodas() );
		m.put( "clientes" , this.clservice.leerTodos() );
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView("informe_factura", m);
	}
	
	@RequestMapping("informe_factura_listado.do")
	public ModelAndView listadoFacturas(InformesFb fb, HttpSession s) 
	{
		TUsuario u				= (TUsuario) s.getAttribute("usuario");
		String rol				= u.getTRol().getDescr();
		Integer oid				= rol.equals("admin") || rol.equals("factura") ? null : u.getOid();
		List<TFactura> facturas	= this.fservice.leerFacturasFechas( fb.getFini(), fb.getFfin(), fb.getOidempresa(), fb.getOidcliente(), oid );
		ModelMap m 				= new ModelMap();
		double[] bit			= this.baseIvaTotal(facturas);
		
		m.put( "facturas", facturas );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		m.put( "base", bit[0] );
		m.put( "iva", bit[1] );
		m.put( "total", bit[2] );
		
		return new ModelAndView("informe_factura_listado", m);
	}
	
	@RequestMapping("informe_factura_pdf.do")
	public void facturaPdf(int oid, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		ContratosBuscarFb fb	= new ContratosBuscarFb();
		
		fb.setOid(oid);
		
		Descargar desc			= new Descargar();
		File pdfFile			= this.xservice.leer(fb);
		
		desc.flush(pdfFile, resp);
	}
	
	@RequestMapping("informe_estados.do")
	public ModelAndView estados() 
	{
		ModelMap m 	= new ModelMap();
		Date d 		= new Date();
		
		m.put( "empresas", this.eservice.leerTodas() );
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView("informe_estados", m);
	}
	
	@RequestMapping("informe_estados_listado.do")
	public ModelAndView listadoEstados(InformesFb fb, HttpSession s) 
	{
		TUsuario u	= (TUsuario) s.getAttribute("usuario");
		String rol	= u.getTRol().getDescr();
		Integer oid	= rol.equals("admin") || rol.equals("factura") ? null : u.getOid();
		
		List<TFactura> facturas					= this.fservice.leerFacturasFechas( fb.getFini(), fb.getFfin(), fb.getOidempresa(), null, oid );
		Map<Integer, List<TFactura>> efacturas 	= facturas.stream().collect( Collectors.groupingBy( x -> x.getTEstado().getOid() ) );
		List<TFactura> pagadas					= efacturas.getOrDefault( 2, new LinkedList<>() );
		List<TFactura> sinpagar					= efacturas.getOrDefault( 1, new LinkedList<>() );
		
		ModelMap m		= new ModelMap();
		double[] bitp	= this.baseIvaTotal(pagadas);
		double[] bits	= this.baseIvaTotal(sinpagar);

		m.put( "pagadas", pagadas );
		m.put( "sinpagar", sinpagar );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		m.put( "pbase", bitp[0] );
		m.put( "piva", bitp[1] );
		m.put( "ptotal", bitp[2] );
		m.put( "sbase", bits[0] );
		m.put( "siva", bits[1] );
		m.put( "stotal", bits[2] );
		
		return new ModelAndView("informe_estados_listado", m);
	}
	
	@RequestMapping("informe_resumen.do")
	public ModelAndView informeResumen() 
	{
		ModelAndView m 	= this.estados();
		Date d 			= new Date();
		
		m.setViewName("informe_resumen");
		
		m.getModel().put( "fini" , d );
		m.getModel().put( "ffin" , d );
		
		return m;
	}
	
	@RequestMapping("informe_resumen_listado.do")
	public ModelAndView listadoResumen(InformesFb fb, HttpSession s) 
	{
		ModelAndView mav1	= this.listadoEstados(fb, s);
		ModelAndView mav2	= this.listado(fb, s);
		
		mav1.setViewName("informe_resumen_listado");
		
		mav1.addAllObjects( mav2.getModel() );
		
		return mav1;
	}
	
	@RequestMapping("informe_partes.do")
	public ModelAndView partes() 
	{
		ModelMap m 	= new ModelMap();
		Date d		= new Date();
		
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView("informe_partes", m);
	}
	
	@RequestMapping("informe_partes_listado.do")
	public ModelAndView listadoPartes(InformesFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		ModelMap m 					= new ModelMap();
		List<TParte> lp				= null;
		List<TMatrimonio> lm		= null;
		Map<Integer, Double> total 	= new HashMap<>();
		Map<Integer, String> n2 	= new HashMap<>();
		Set<Integer> partesl		= new HashSet<>();
		
		lp 	= this.ptservice.leerPartesBuscar( fb.getFini(), fb.getFfin() );
		lm 	= this.mservice.leer(lp);
		
		// Sólo queremos los matrimonios con facturas
		lm	= lm.stream().filter( r -> r.getNumero2() != null ).collect( Collectors.toList() );
		
		// Para cada parte obtenemos su numero2
		n2.putAll
		( 
			lm.stream().collect( Collectors.toMap( TMatrimonio::getParte, TMatrimonio::getNumero2 ) )
		);
		
		// Para cada parte obtenemos su total 
		lp.forEach
		( 
			p -> 
			{
				total.put( p.getOid(), this.totalParte(p) );
			} 
		);
		
		// Identificamos los partes con factura SL
		partesl.addAll
		(
			lm.stream().filter( p -> p.getNumero2().matches("08\\d{8}") ).map( TMatrimonio::getParte ).collect( Collectors.toSet() )
		);
			
		m.put( "partes", lp );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		m.put( "totales", total );
		m.put( "partesl", partesl );
		m.put( "n2", n2 );
		
		return new ModelAndView("informe_partes_listado", m);
	}
	
	private double[] baseIvaTotal(List<TFactura> facturas)
	{
		double base		= facturas.stream().mapToDouble( TFactura::getBase ).sum();
		double iva 		= facturas.stream().mapToDouble( TFactura::getBaseIva ).sum();
		double total	= base + iva;
		
		return new double[]{base, iva, total};
	}
	
	/**
	 * Calculamos el total del parte.
	 * Total parte = Suma total filas del parte.
	 * Total filas del parte = suma de todos los precios ... En teoría todos serán null y 1 tendrá valor
	 * @param p		Parte
	 * @return		Total de parte 
	 */
	private double totalParte(TParte p)
	{
		double[] total = { 0d };
		
		p.getTParteLineas().forEach
		(
			l ->
			{
				double tmp = 0;
				
				tmp += this.getTotalLimpio( l.getPrecio() );
				tmp += this.getTotalLimpio( l.getPrecioCentral() );
				tmp += this.getTotalLimpio( l.getPrecioDetectores() );
				tmp += this.getTotalLimpio( l.getPrecioEquipoAuxiliar() );
				tmp += this.getTotalLimpio( l.getPrecioFuente() );
				tmp += this.getTotalLimpio( l.getPrecioPuertas() );
				tmp += this.getTotalLimpio( l.getPrecioPulsadores() );
				tmp += this.getTotalLimpio( l.getPrecioRetenedor() );
				tmp += this.getTotalLimpio( l.getPrecioSirenas() );
				
				total[0] += tmp;
			}
		);
		
		return total[0];
	}
	
	/**
	 * Como tenemos un Double, puede ser nulo.
	 * @param d		Double
	 * @return		Su valor o 0
	 */
	private double getTotalLimpio(Double d)
	{
		return d != null ? d : 0.0;
	}
	
	@RequestMapping("informe_contrato2_buscar.do")
	public ModelAndView informeContrato2Buscar() 
	{
		ModelMap m 	= new ModelMap();
		Date d 		= new Date();
		
		m.put( "fini" , d );
		m.put( "ffin" , d );
		
		return new ModelAndView("informe_contrato2_buscar", m);
	}
	
	@RequestMapping("informe_contrato2_listado.do")
	public ModelAndView informeContrato2Listado(InformesFb fb) 
	{
		List<TContrato> contratos 	= this.cservice.leerContratosFechas( fb.getFini(), fb.getFfin(), null );
		ModelMap m 					= new ModelMap();
		Map<Integer, Double> precio	= new HashMap<>();
		
		contratos.forEach
			( 
				c -> precio.put
						( 
							c.getOid(), 
							c.getTLineaContratos().stream().mapToDouble( t -> t.getPrecio() ).sum() 
						)
			);
		
		m.put( "contratos", contratos );
		m.put( "precio", precio );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		
		return new ModelAndView("informe_contrato2_listado", m);
	}
	
	@RequestMapping("informe_factura2_buscar.do")
	public ModelAndView informeFactura2Buscar() 
	{
		ModelAndView m 	= this.estados();
		
		m.setViewName("informe_factura2_buscar");
		
		return m;
	}
	
	@RequestMapping("informe_factura2_listado.do")
	public ModelAndView informeFactura2Listado(InformesFb fb) 
	{
		List<TFactura> facturas		= this.fservice.leerFacturasFechas( fb.getFini(), fb.getFfin(), fb.getOidempresa(), null, null );	
		ModelMap m					= new ModelMap();
		
		m.put( "facturas", facturas );
		m.put( "fini", fb.getFini() );
		m.put( "ffin", fb.getFfin() );
		
		return new ModelAndView("informe_factura2_listado", m);
	}
}
