package com.atz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atz.fb.ContratosBuscarFb;
import com.atz.fb.ContratosFb;
import com.atz.pdf.Descargar;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TEmpresa;
import com.atz.persistencia.TFactura;
import com.atz.persistencia.TFacturaLinea;
import com.atz.persistencia.TUsuario;
import com.atz.service.ClienteService;
import com.atz.service.EmpresaService;
import com.atz.service.EstadosService;
import com.atz.service.FacturaService;
import com.atz.service.PdfFacturaService;
import com.atz.service.SendMailService;
import com.atz.service.UsuarioService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Factura 
{
	@Autowired
	private UsuarioService uservice;
	
	@Autowired
	private FacturaService fservice;
	
	@Autowired
	private ClienteService cservice;
	
	@Autowired
	private PdfFacturaService pservice;
	
	@Autowired
	private EmpresaService eservice;
	
	@Autowired
	private SendMailService smservice;
	
	@Autowired
	private EstadosService stservice;
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
	} 
	
	@RequestMapping("factura_buscar.do")
	public ModelAndView buscar(HttpSession s) 
	{
		TUsuario u	= (TUsuario) s.getAttribute("usuario");
		ModelMap m 	= new ModelMap();
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "empresas", this.eservice.leerTodas() );
		m.put( "fini", new Date() );
		m.put( "ffin", new Date() );
		
		return new ModelAndView("factura_buscar", m);
	}
	
	@RequestMapping("factura_buscar_listado.do")
	public ModelAndView cargar(ContratosBuscarFb fb) 
	{
		TCliente c 				= this.cservice.leer( fb.getOidcliente() );
		List<TFactura> cont		= this.fservice.leerFacturas(fb);
		ModelMap m 				= new ModelMap();
		
		m.put("contratos", cont);
		m.put("cliente", c);
		
		return new ModelAndView("factura_buscar_listado", m);
	}
	
	@RequestMapping("factura_crear.do")
	public ModelAndView crear(Integer oid) 
	{
		ModelMap m = new ModelMap("oidclienteload", oid);
		
		return new ModelAndView( "factura_crear", m);
	}
	
	@RequestMapping("factura_crear_filas.do")
	public ModelAndView crearFilas(HttpSession s) 
	{
		ModelMap m 			= new ModelMap();
		List<TEmpresa> lemp	= this.eservice.leerTodas();
		Integer n			= this.fservice.maxNumero( lemp.get(0).getOid() );	
		TFactura c			= new TFactura();
		Date ahora			= new Date();
		
		c.setIva(21D);
		c.setNumero(n);
		c.setFecha(ahora);
		c.getTFacturaLineas().add( new TFacturaLinea() );
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "factura", c );
		m.put( "empresas", lemp );	
		m.put( "estados", this.stservice.getTodos() );
		
		return new ModelAndView( "factura_crear_filas", m );
	}
	
	@RequestMapping("factura_formulario.do")
	public ModelAndView contratoFormulario() 
	{
		return new ModelAndView("factura_formulario");
	}
	
	@RequestMapping("factura_crear_guardar.do")
	public ModelAndView crearGuardar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Integer n				= this.fservice.maxNumero( fb.getOidempresa() );
		
		fb.setNumero(n);
		
		TFactura c				= this.fservice.crear(fb);
		ContratosBuscarFb cb 	= new ContratosBuscarFb();
				
		cb.setOid( c.getOid() );
		
		return this.buscar(cb, s);
	}
	
	@RequestMapping("factura_buscar_cargar.do")
	public ModelAndView buscar(ContratosBuscarFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TFactura c = this.fservice.leer( fb.getOid() );
		
		return this.cargarFactura(c, s);
	}
	
	@RequestMapping("factura_buscar_guardar.do")
	public ModelAndView buscarGuardar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TFactura c = this.fservice.actualizar(fb);
		
		return this.cargarFactura(c, s);
	}
	
	private ModelAndView cargarFactura(TFactura c, HttpSession s)
	{
		ModelMap m		= new ModelMap();
		
		m.put( "clientes", this.cservice.leerTodos() );
		m.put( "empresas", this.eservice.leerTodas() );
		m.put( "factura", c );
		m.put( "estados", this.stservice.getTodos() );
		
		return new ModelAndView("factura_buscar_cargar", m);
	}
	
	@RequestMapping("factura_buscar_cargar_filas.do")
	public ModelAndView buscarFilas() 
	{
		return new ModelAndView("factura_buscar_cargar_filas");
	}
	
	@RequestMapping("factura_borrar.do")
	public ModelAndView borrar(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		this.fservice.borrar( fb.getOid() );
		
		return this.buscar(s);
	}
	
	@RequestMapping("factura_pdf.do")
	public void pdf(ContratosBuscarFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		File pdfFile	= this.pservice.crear(fb);
		Descargar desc	= new Descargar();
		
		desc.flush( pdfFile, resp );
	}
	
	@RequestMapping("factura_email.do")
	public void email(ContratosBuscarFb fb, HttpServletResponse resp) 
	throws IllegalAccessException, InvocationTargetException, JRException, IOException 
	{
		File pdfFile	= this.pservice.crear(fb);	
		TCliente c 		= this.cservice.leer( fb.getOidcliente() );
		String mensaje	= "Mensaje enviado";
		PrintWriter out = resp.getWriter();
		
		try 
		{
			this.smservice.enviarSinCC( c, fb.getCcemail(), pdfFile );
			this.fservice.actualizarFechaEnvio( fb.getOid() );
		} 
		catch(MessagingException e)
		{
			mensaje		= "Error al enviar el mensaje";
		}
		
		out.println(mensaje);
		out.close();
	}
	
	@RequestMapping("factura_siguiente_numero.do")
	@ResponseBody
	public String siguienteNumero(int oid) 
	throws IllegalAccessException, InvocationTargetException 
	{
		Integer n	= this.fservice.maxNumero(oid);
		String resp	= "{\"oid\": x}";
		
		return resp.replace( "x", n.toString() );
	}
	
	@RequestMapping("factura_copia.do")
	public ModelAndView copia(ContratosFb fb, HttpSession s) 
	throws IllegalAccessException, InvocationTargetException 
	{
		fb.setNumero( this.fservice.maxNumero( fb.getOidempresa() ) );
		fb.setOid(null);
		
		return this.crearGuardar(fb, s);
	}
}
