package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.atz.dao.AgenteDAO;
import com.atz.dao.ClienteDAO;
import com.atz.dao.ContratoDAO;
import com.atz.dao.LineaContratoDAO;
import com.atz.dao.PreguntasDAO;
import com.atz.dao.PruebaDAO;
import com.atz.fb.ContratosFb;
import com.atz.fb.PartesFb;
import com.atz.persistencia.TAgente;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TLineaContrato;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TPreguntasContrato;
import com.atz.persistencia.TPrueba;
import com.atz.util.Utils;
import com.atz.validation.Formato1;

@Service
public class ContratoService 
{
	@Autowired
	private ContratoDAO cdao;
	
	@Autowired
	private ClienteDAO kdao;
	
	@Autowired
	private AgenteDAO adao;
	
	@Autowired
	private PruebaDAO pdao;
	
	@Autowired
	private LineaContratoDAO ldao;
	
	@Autowired
	private PreguntasDAO qdao;
	
	@Autowired
	private ParteService pservice;
	
	@Autowired
	private AgenteService aservice;
	
	@Autowired
	private Validator validator;
	
	@Transactional(readOnly=true)
	public List<TContrato> leerTodos()
	{
		return this.cdao.readAll();
	}
	
	@Transactional(readOnly=true)
	public TContrato leer(int oid)
	{
		return this.cdao.read(oid);
	}
	
	@Transactional(readOnly=true)
	public List<TContrato> leerContratosCliente(int oid)
	{
		return this.cdao.readContratosCliente(oid);
	}
	
	@Transactional(readOnly=true)
	public List<TContrato> leerContratosFechas(Date fini, Date ffin, Integer oid)
	{
		return this.cdao.readContratosFechas(fini, ffin, oid);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public synchronized TContrato crear(ContratosFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TContrato tc = new TContrato();
		
		this.copy(fb, tc);
		
		tc.setAuditoria1( new Date() );
		tc.setAuditoria2( new Date() );
		
		return this.cdao.create(tc);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void borrar(int u) 
	throws IllegalAccessException, InvocationTargetException
	{
		this.cdao.delete(u);
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public TContrato actualizar(ContratosFb fb) 
	throws IllegalAccessException, InvocationTargetException
	{
		TContrato tc = this.cdao.read( fb.getOid() );
		
		for( TLineaContrato lc : tc.getTLineaContratos() )
		{
			this.ldao.delete(lc);
		}
		
		tc.getTLineaContratos().clear();
		
		for( TPreguntasContrato pc : tc.getTPreguntasContratos() )
		{
			this.qdao.delete(pc);
		}
		
		tc.getTPreguntasContratos().clear();
		
		this.copy(fb, tc);
		
		tc.setAuditoria2(new Date());
		
		this.cdao.update(tc);
		
		return tc;
	}
	
	@Transactional(readOnly=true, isolation=Isolation.DEFAULT)
	public List<TPrueba> leerPruebas()	
	{
		return this.pdao.getAll();
	}
	
	@Transactional(readOnly=true, isolation=Isolation.DEFAULT)
	public List<TAgente> leerAgentes()
	{
		return this.adao.getAll();
	}
	
	@Transactional(readOnly=true, isolation=Isolation.DEFAULT)
	public Integer maxNumero()
	{
		return this.cdao.readSiguienteNumero();
	}
	
	@Transactional(readOnly=false, isolation=Isolation.DEFAULT)
	public void actualizaAuditoriaEmail(int oid) 
	throws IllegalAccessException, InvocationTargetException {
		TContrato tp = this.cdao.read(oid);
		tp.setAuditoriaEmail(new Date());
		
		this.cdao.update(tp);
	}
	
	private void copy(ContratosFb fb, TContrato tc)
	{
		tc.setTCliente( this.kdao.read( fb.getOidcliente() ) );
		tc.setFecha( fb.getFecha() );
		tc.setNumero( fb.getNumero() );
		tc.setAnexo( fb.getAnexo() );
		tc.setDireccion( fb.getDireccion() );
		tc.setTrimestral( fb.getTrimestral() );
		tc.setCcEmail( fb.getCcemail() );
		
		List<Integer> cantidad = Arrays.asList(fb.getCantidadExt()).stream().filter(x -> x != null && x > 0).collect(Collectors.toList());
		
		for(int i=0; i<cantidad.size(); i++)
		{
			TLineaContrato tlc = new TLineaContrato();
			
			tlc.setNumeroPlaca( fb.getNumeroPlacaExt()[i] );
			tlc.setCapacidad( fb.getCapacidadExt()[i] );
			tlc.setFechaFab( fb.getFechaFabExt()[i] );
			tlc.setFechaRet( fb.getFechaRetExt()[i] );
			tlc.setPrecio( fb.getPrecioExt()[i] );
			tlc.setCantidad( fb.getCantidadExt()[i] );
			
			if(fb.getAgentesExt()[i] != null) {
				tlc.setTAgente( this.adao.get( fb.getAgentesExt()[i] ) );
			}
			
			if(fb.getPruebasExt()[i] != null) {
				tlc.setTPrueba( this.pdao.get( fb.getPruebasExt()[i] ) );
			}
			
			tlc.setDescr( fb.getDescrExt()[i] );
			tlc.setFabricante( fb.getFabricanteExt()[i] );
			
			tlc.setTContrato(tc);
			tc.getTLineaContratos().add(tlc);
		}
		
		if( fb.getPreguntas() != null )
		{
			for( String p : fb.getPreguntas() )
			{
				String[] partes 		= p.split("_");
				int oidpregunta			= Integer.valueOf( partes[0] );
				int oidrespuesta		= Integer.valueOf( partes[1] );
				
				TPreguntasContrato tpc 	= new TPreguntasContrato();
				
				tpc.setTContrato(tc);
				tpc.setTPreguntas( this.qdao.getQuestion(oidpregunta) );
				tpc.setTPreguntasRespuestas( this.qdao.getAswer(oidrespuesta) );
				
				tc.getTPreguntasContratos().add(tpc);
			}
		}
		
		if(tc.getTLineaContratos().size() == 0) {
			TLineaContrato tlc = new TLineaContrato();
			tlc.setFechaFab(new Date());
			tlc.setTAgente(this.aservice.getByOid(1));
			
			tc.getTLineaContratos().add(tlc);
		}
	}
	
	@Transactional(readOnly=false)
	public TParte crearParte(ContratosFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
	
		SpringValidatorAdapter springValidator 				= new SpringValidatorAdapter(validator);
		Set<ConstraintViolation<ContratosFb>> violations 	= springValidator.validate(fb, Formato1.class);
		
		if( !violations.isEmpty() ) 
		{
			throw new ConstraintViolationException("Contrato no válido", violations);
		}
		
		TCliente cl	= this.kdao.read(fb.getOidcliente());
		PartesFb p	= this.crearParte(fb, cl);
		
		return this.pservice.crear(p);
		
	}

	private PartesFb crearParte(ContratosFb fb, TCliente cl) 
	{
		PartesFb p = new PartesFb();
		
		Integer numLin = fb.getCantidadExt().length;
		
		p.init();
		
		p.setFecha( new Date() );
		p.setOidcliente(cl.getOid());
		p.setOidusuario(fb.getOidusuario());
		p.setOidpartetipo(Utils.detectTipoParte(fb));
		p.setNumero(this.pservice.maxNumero());
		p.setPrecio(fb.getPrecioExt());
		p.setOidestadoparte(2);
		p.setOrden(fb.getCantidadExt());
		p.setTipo(fb.getAgentesExt());
		p.setUbicacion(fb.getDescrExt());
		p.setObservaciones(fb.getAnexo());
		
		if(p.getOidpartetipo() == 1) {
			
			String[] numPlaca = new String[numLin];
			for(int i = 0; i < fb.getNumeroPlacaExt().length; i++) {
				numPlaca[i] = fb.getNumeroPlacaExt()[i] == null ? null : fb.getNumeroPlacaExt()[i];
			}
			p.setNumPlaca(numPlaca);
			Integer[] kg = new Integer[numLin];
			for(int i = 0; i < fb.getNumeroPlacaExt().length; i++) {
				kg[i] = fb.getCapacidadExt()[i] == null ? null : fb.getCapacidadExt()[i].intValue();
			}
			p.setKg(kg);
			p.setFabricante(fb.getFabricanteExt());
			Date[] fechFab = new Date[numLin];
			for(int i = 0; i < fb.getFechaFabExt().length; i++) {
				fechFab[i] = fb.getFechaFabExt()[i] == null ? new Date() : fb.getFechaFabExt()[i]; 
			}
			p.setFechaFabricacion(fechFab);
			p.setUltimoRetimbrado(fb.getFechaRetExt());
			p.setPrueba(fb.getPruebasExt());
			p.setCartel(new Date[numLin]);
			p.setAltura(new String[numLin]);
			p.setPrecio(fb.getPrecioExt());
		
		} else if(p.getOidpartetipo() == 2) {
			
			String[] longmang = new String[numLin];
			for(int i = 0; i < fb.getNumeroPlacaExt().length; i++) {
				longmang[i] = fb.getAgentesExt()[i] == null ? null : this.adao.get(fb.getAgentesExt()[i]).getDescr().substring(4);
			}
			p.setLongMang(longmang);
			Date[] fechFab = new Date[numLin];
			for(int i = 0; i < fb.getFechaFabExt().length; i++) {
				fechFab[i] = fb.getFechaFabExt()[i] == null ? new Date() : fb.getFechaFabExt()[i]; 
			}
			p.setFechaRetimA(fechFab);
			p.setFechaRetimB(fb.getFechaRetExt());
			p.setPresionDinamica(new Double[numLin]);
			p.setPresionEstatica(new Double[numLin]);
			p.setManguera(new String[numLin]);
			p.setLanza(new String[numLin]);
			p.setValvula(new String[numLin]);
			p.setManometro(new String[numLin]);
			p.setCristal(new String[numLin]);
			p.setSennales(new String[numLin]);
			p.setEstadoGeneral(new String[numLin]);
			p.setNumSerie(new String[numLin]);
		} else if(p.getOidpartetipo() == 5) {
			List<Integer> agentes = Arrays.asList(fb.getAgentesExt());
			int ordenCentral = 0, ordenFuente = 0, ordenDetectores = 0, ordenPulsadores = 0, ordenSirenas = 0, ordenEquipoAuxiliar = 0;
			List<String> ubiDet = new ArrayList<>(), ubiPulsadores = new ArrayList<>(), ubiSirenas = new ArrayList<>(), ubiEquipoAuxiliar = new ArrayList<>();
			List<Double> precioCentral = new ArrayList<>(), precioDet = new ArrayList<>(), precioPulsadores = new ArrayList<>(), precioSirenas = new ArrayList<>(), precioEquipoAuxiliar = new ArrayList<>();
			
			
			for(Integer agente : agentes) {
				
				if(this.aservice.getOidByDescr("Central de incendio").equals(agente) || this.aservice.getOidByDescr("Central de monóxido").equals(agente)) {
					ordenCentral++;
					precioCentral.add(fb.getPrecioExt()[agentes.indexOf(agente)]);
				} else if(this.aservice.getOidByDescr("Detector").equals(agente)) {
					ordenDetectores++;
					ubiDet.add(fb.getDescrExt()[agentes.indexOf(agente)]);
					precioDet.add(fb.getPrecioExt()[agentes.indexOf(agente)]);
				} else if(this.aservice.getOidByDescr("Pulsador").equals(agente)) {
					ordenPulsadores++;
					ubiPulsadores.add(fb.getDescrExt()[agentes.indexOf(agente)]);
					precioPulsadores.add(fb.getPrecioExt()[agentes.indexOf(agente)]);
				} else if(this.aservice.getOidByDescr("Sirena").equals(agente)) {
					ordenSirenas++;
					ubiSirenas.add(fb.getDescrExt()[agentes.indexOf(agente)]);
					precioSirenas.add(fb.getPrecioExt()[agentes.indexOf(agente)]);
				} else if(this.aservice.getOidByDescr("Equipo auxiliar").equals(agente)) {
					ordenEquipoAuxiliar++;
					ubiEquipoAuxiliar.add(fb.getDescrExt()[agentes.indexOf(agente)]);
					precioEquipoAuxiliar.add(fb.getPrecioExt()[agentes.indexOf(agente)]);
				}
			}
			
			Integer[] ordCen = new Integer[ordenCentral];
			while(ordenCentral > 0) {
				ordCen[ordenCentral - 1] = ordenCentral;
				ordenCentral--;
			}
			
			Integer[] ordDet = new Integer[ordenDetectores];
			while(ordenDetectores > 0) {
				ordDet[ordenDetectores - 1] = ordenDetectores;
				ordenDetectores--;
			}
			
			Integer[] ordPul = new Integer[ordenPulsadores];
			while(ordenPulsadores > 0) {
				ordPul[ordenPulsadores - 1] = ordenPulsadores;
				ordenPulsadores--;
			}
			
			Integer[] ordSir = new Integer[ordenSirenas];
			while(ordenSirenas > 0) {
				ordSir[ordenSirenas - 1] = ordenSirenas;
				ordenSirenas--;
			}
			
			Integer[] ordEquAux = new Integer[ordenEquipoAuxiliar];
			while(ordenEquipoAuxiliar > 0) {
				ordEquAux[ordenEquipoAuxiliar - 1] = ordenEquipoAuxiliar;
				ordenEquipoAuxiliar--;
			}
			
			p.setOrdenCentral(ordCen);
			p.setOrdenDetectores(ordDet);
			p.setOrdenPulsadores(ordPul);
			p.setOrdenSirenas(ordSir);
			p.setOrdenEquipoAuxiliar(ordEquAux);
			
			String[] det = new String[ubiDet.size()], pul = new String[ubiPulsadores.size()], sir = new String[ubiSirenas.size()], equAux = new String[ubiEquipoAuxiliar.size()];
			ubiDet.toArray(det);
			ubiPulsadores.toArray(pul);
			ubiSirenas.toArray(sir);
			ubiEquipoAuxiliar.toArray(equAux);
			
			p.setUbiDetectores(det);
			p.setUbiPulsadores(pul);
			p.setUbiSirenas(sir);
			p.setUbicacionEquipoAuxiliar(equAux);
			
			Double[] prCen = new Double[precioCentral.size()], prDet = new Double[precioDet.size()], prPul = new Double[precioPulsadores.size()], prSir = new Double[precioSirenas.size()], prEquAux = new Double[precioEquipoAuxiliar.size()];
			precioCentral.toArray(prCen);
			precioDet.toArray(prDet);
			precioPulsadores.toArray(prPul);
			precioSirenas.toArray(prSir);
			precioEquipoAuxiliar.toArray(prEquAux);
			
			p.setPrecioCentral(prCen);
			p.setPrecioDetectores(prDet);
			p.setPrecioPulsadores(prPul);
			p.setPrecioSirenas(prSir);
			p.setPrecioEquipoAuxiliar(prEquAux);
		}
		
		p.setPreguntas(fb.getPreguntas());
		
		
		return p;
	}
}
