package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.ClienteDAO;
import com.atz.dao.EmpresaDAO;
import com.atz.dao.EstadoDAO;
import com.atz.dao.FacturaDAO;
import com.atz.dao.FacturaLineaDAO;
import com.atz.fb.ContratosBuscarFb;
import com.atz.fb.ContratosFb;
import com.atz.fb.PartesFb;
import com.atz.persistencia.TFactura;
import com.atz.persistencia.TFacturaLinea;
import com.atz.persistencia.TMatrimonio;

@Service
public class FacturaService 
{
	@Autowired
	private FacturaDAO fdao;
	
	@Autowired
	private ClienteDAO kdao;
	
	@Autowired
	private FacturaLineaDAO ldao;
	
	@Autowired
	private EmpresaDAO edao;
	
	@Autowired
	private EstadoDAO stdao;
		
	@Transactional(readOnly=true)
	public List<TFactura> leerTodos()
	{
		return this.fdao.readAll();
	}
	
	@Transactional(readOnly=true)
	public TFactura leer(int oid)
	{
		return this.fdao.read(oid);
	}
	
	@Transactional(readOnly=true)
	public TFactura leerN2(String n2)
	{
		List<TFactura> tmp = this.fdao.readFacturasNumero2( Arrays.asList(n2) );
		
		return tmp.isEmpty() ? null : tmp.get(0);
	}
	
	@Transactional(readOnly=true)
	public List<TFactura> leerFacturasCliente(ContratosBuscarFb fb)
	{
		return this.fdao.readFacturasCliente( fb.getOidcliente() );
	}
	
	@Transactional(readOnly=true)
	public List<TFactura> leerFacturasFechas(Date fini, Date ffin, Integer oidempresa, Integer oidcliente, Integer oid)
	{
		List<TFactura> tmp = this.fdao.readFacturasFechas(fini, ffin, oidempresa, oidcliente, oid);
		
		tmp.forEach( t -> Hibernate.initialize( t.getTEstado() ) );
		 
		return tmp;
	}
	
	@Transactional(readOnly=true)
	public List<TFactura> leerFacturas(ContratosBuscarFb fb)
	{
		return this.fdao.readFacturas
				( 
					fb.getFini() , 
					fb.getFfin(), 
					fb.getOidempresa(), 
					fb.getOidcliente(), 
					fb.getNumero() 
				);
	}
	
	@Transactional(readOnly=true)
	public Map<String, Boolean> leerFacturasEstado(List<TMatrimonio> lm)
	{
		List<String> ln2 				= lm.stream().map( t -> t.getNumero2() ).filter( t -> t != null ).collect( Collectors.toList() );
		List<TFactura> lf				= this.fdao.readFacturasNumero2(ln2);
		Map<String, Boolean> pagadas 	= new HashMap<>();
		
		// PAGADAS = Todo lo que no sea SIN PAGAR
		lf.forEach( t -> pagadas.put( t.getNumero2(),  t.getTEstado().getOid() != 1 ) );
		
		return pagadas;
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public synchronized TFactura crear(ContratosFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TFactura tc = new TFactura();
		
		this.copy(fb, tc);
		
		tc.setAuditoria1( new Date() );
		tc.setAuditoria2( new Date() );
		tc.setNumero2( this.getNumero2(tc) );
		
		return this.fdao.create(tc);
	}
	
	private String getNumero2(TFactura tc)
	{
		Calendar cal 		= Calendar.getInstance();
		DecimalFormat fmt4 	= new DecimalFormat("0000");
		DecimalFormat fmt2 	= new DecimalFormat("00");
		StringBuffer n2		= new StringBuffer();
		
		cal.setTime( tc.getFecha() );

		n2.append( fmt2.format(tc.getTEmpresa().getOid() ) );
		n2.append( cal.get( Calendar.YEAR ) );
		n2.append( fmt4.format( tc.getNumero() ) );
		
		return n2.toString();
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TFactura crearSl(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		int oidempresa	= 8;
		double iva		= 21D;
		
		return this.crearFactura(fb, oidempresa, iva);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TFactura crearCarpinteria(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		int oidempresa	= 7;
		double iva		= 0D;
		
		return this.crearFactura(fb, oidempresa, iva);
	}
	
	public TFactura crearFactura(PartesFb fb, int oidempresa, double iva) 
	throws IllegalAccessException, InvocationTargetException
	{
		String descr		= "TRABAJO REALIZADO CON PARTE " + fb.getNumero() + " BASÁNDOSE EN EL RD 513/2017.";
		TFactura tc			= new TFactura();
		TFacturaLinea tlc 	= new TFacturaLinea();
		
		double t1			= this.totalParte( fb.getPrecio() );
		double t2			= this.totalParte( fb.getPrecioCentral() );
		double t3			= this.totalParte( fb.getPrecioDetectores() );
		double t4			= this.totalParte( fb.getPrecioEquipoAuxiliar() );
		double t5			= this.totalParte( fb.getPrecioFuente() );
		double t6			= this.totalParte( fb.getPrecioPuertas() );
		double t7			= this.totalParte( fb.getPrecioPulsadores() );
		double t8			= this.totalParte( fb.getPrecioRetenedor() );
		double t9			= this.totalParte( fb.getPrecioSirenas() );
		
		tc.setTCliente( this.kdao.read( fb.getOidcliente() ) );
		tc.setTCliente2( tc.getTCliente() );
		tc.setFecha( new Date() );
		tc.setTEmpresa( this.edao.read(oidempresa) );
		tc.setNumero( this.maxNumero(oidempresa) );
		tc.setIva(iva);
		
		tlc.setPrecio( t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 );
		tlc.setCantidad( 1 );
		tlc.setDescripcion( descr );
			
		tlc.setTFactura(tc);
		tc.getTFacturaLineas().add(tlc);
		
		tc.setAuditoria1( new Date() );
		tc.setAuditoria2( new Date() );
		
		tc.setNumero2( this.getNumero2(tc) );
		
		tc.setTEstado( this.stdao.get(1) );	// Sin pagar
		
		return this.fdao.create(tc);
	}
	
	private double totalParte(Double[] arr)
	{
		return 	arr == null ? 
				0D : 
				Arrays.stream( arr ).filter( t -> t != null ).mapToDouble( t -> t.doubleValue() ).sum();
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void borrar(int u) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.fdao.delete(u);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TFactura actualizar(ContratosFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TFactura tc = this.fdao.read( fb.getOid() );
		
		for( TFacturaLinea lc : tc.getTFacturaLineas() )
		{
			this.ldao.delete(lc);
		}
		
		tc.getTFacturaLineas().clear();
		
		this.copy(fb, tc);
		
		tc.setAuditoria2( new Date() );
		tc.setNumero2( this.getNumero2(tc) );
		
		this.fdao.update(tc);
		
		return tc;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.DEFAULT)
	public Integer maxNumero(int oidempresa)
	{
		return this.fdao.readSiguienteNumero(oidempresa);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void actualizarFechaEnvio(Integer oid)
	{
		TFactura f = this.fdao.read(oid);
		f.setEnvio(new Timestamp(new Date().getTime()));
		this.fdao.update(f);
	}
	
	private void copy(ContratosFb fb, TFactura tc)
	{
		tc.setTCliente( this.kdao.read( fb.getOidcliente() ) );
		tc.setTCliente2( this.kdao.read( fb.getOidcliente2() ) );
		tc.setTEstado( this.stdao.get( fb.getOidestado() ) );
		tc.setFecha( fb.getFecha() );
		tc.setNumero( fb.getNumero() );
		tc.setIva( fb.getIva() );
		tc.setTEmpresa( this.edao.read( fb.getOidempresa() ) );
		tc.setCcEmail( fb.getCcemail() );
		
		for(int i=0; i<fb.getCantidadExt().length; i++)
		{
			TFacturaLinea tlc = new TFacturaLinea();
			
			tlc.setPrecio( fb.getPrecioExt()[i] );
			tlc.setCantidad( fb.getCantidadExt()[i] );
			tlc.setDescripcion( fb.getDescrExt()[i] );
			tlc.setDescuento( fb.getDescuentoExt()[i] );
			
			tlc.setTFactura(tc);
			tc.getTFacturaLineas().add(tlc);
		}
	}
}
