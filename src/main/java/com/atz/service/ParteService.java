package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.ClienteDAO;
import com.atz.dao.EstadoDAO;
import com.atz.dao.EstadoParteDAO;
import com.atz.dao.ParteCambiosDAO;
import com.atz.dao.ParteDAO;
import com.atz.dao.ParteLineaDAO;
import com.atz.dao.ParteModalidadDAO;
import com.atz.dao.PartePeriodicidadDAO;
import com.atz.dao.ParteTipoDAO;
import com.atz.dao.PreguntasDAO;
import com.atz.dao.TipoBieBombaDAO;
import com.atz.dao.TipoBieDAO;
import com.atz.dao.TipoExtintorDAO;
import com.atz.dao.UsuarioDAO;
import com.atz.fb.ContratosFb;
import com.atz.fb.ParteBuscarFb;
import com.atz.fb.PartesFb;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
import com.atz.persistencia.TMatrimonio;
import com.atz.persistencia.TParte;
import com.atz.persistencia.TParteLinea;
import com.atz.persistencia.TPreguntasParte;

@Service
public class ParteService {
	@Autowired
	private ParteDAO pdao;

	@Autowired
	private ClienteDAO kdao;

	@Autowired
	private UsuarioDAO udao;

	@Autowired
	private ParteLineaDAO pldao;

	@Autowired
	private ParteTipoDAO ptdao;

	@Autowired
	private ParteCambiosDAO pcdao;

	@Autowired
	private TipoExtintorDAO tedao;

	@Autowired
	private TipoBieDAO tbdao;
	
	@Autowired
	private TipoBieBombaDAO tbbdao;

	@Autowired
	private EstadoParteDAO epdao;
	
	@Autowired
	private EstadoDAO edao;

	@Autowired
	private PreguntasDAO qdao;

	@Autowired
	private ContratoService cservice;

	@Autowired
	private TipoExtintorService teservice;

	@Autowired
	private AgenteService aservice;
	
	@Autowired
	private MatrimonioService mservice;
	
	@Autowired
	private ParteModalidadDAO pmdao;
	
	@Autowired
	private PartePeriodicidadDAO ppdao;

	@Transactional(readOnly = true)
	public List<TParte> leerTodos() {
		return this.pdao.readAll();
	}

	@Transactional(readOnly = true)
	public TParte leer(int oid) 
	{
		TParte p = this.pdao.read(oid);

		Hibernate.initialize(p.getTUsuario());
		Hibernate.initialize(p.getTParteTipo());
		Hibernate.initialize(p.getTPreguntasParte());
		Hibernate.initialize(p.getEstado2());
		Hibernate.initialize(p.getTCliente());
		Hibernate.initialize(p.getTParteModalidad());
		Hibernate.initialize(p.getTPartePeriodicidad());
		
		return p;
	}

	@Transactional(readOnly = true)
	public List<TParte> leerPartesBuscar(ParteBuscarFb fb) {
		return this.pdao.readPartes(fb.getFini(), fb.getFfin(), fb.getOidusuario(), fb.getOidcliente(), fb.getNumero(),
				fb.getOidpartetipo(), fb.getOidestadoparte());
	}

	@Transactional(readOnly = true)
	public List<TParte> leerPartesBuscar(Date fini, Date ffin) {
		return this.pdao.readPartes(fini, ffin);
	}

	@Transactional(readOnly = true)
	public Integer maxNumero() {
		return this.pdao.readSiguienteNumero();
	}

	@Transactional(readOnly = false)
	public TParte crear(PartesFb fb) throws IllegalAccessException, InvocationTargetException {
		// Crear el objeto ...

		TParte tc = new TParte();

		this.copy(fb, tc);

		tc.setAuditoria1(new Date());
		tc.setAuditoria2(new Date());

		tc = this.pdao.create(tc);

		// Registrar en el log ...

		this.pcdao.create(tc);

		return tc;
	}

	@Transactional(readOnly = false)
	public TContrato crearContrato(PartesFb fb) throws IllegalAccessException, InvocationTargetException {
		TCliente cl = this.kdao.read(fb.getOidcliente());
		ContratosFb c = this.crearContrato(fb, cl);

		return this.cservice.crear(c);
	}

	private ContratosFb crearContrato(PartesFb fb, TCliente cl) 
	{
		ContratosFb c = new ContratosFb();
		c.init();
		
		String direccion = ( fb.getDirtra() == null || fb.getDirtra().isEmpty() ) ? cl.getDireccion() : fb.getDirtra(); 

		c.setFecha( new Date() );
		c.setOidcliente( cl.getOid() );
		c.setOidusuario( fb.getOidusuario() );
		c.setDireccion( direccion );
		c.setNumero( this.cservice.maxNumero() );
		c.setAnexo( fb.getObservaciones() );
		c.setTrimestral( 0D );							
		c.setOidmodalidad( fb.getOidmodalidad() );
		c.setOidperiodicidad( fb.getOidperiodicidad() );
		c.setCcemail( fb.getCcemail() );
		c.setPrecio( fb.getCmto() );

		if ( fb.getOidpartetipo() == 1 ) 
		{
			c.setPrecioExt( fb.getPrecio() );

			Date[] fechFab = new Date[ fb.getFechaFabricacion().length ];
			
			for(int i=0; i<fb.getFechaFabricacion().length; i++) 
			{
				fechFab[i] = fb.getFechaFabricacion()[i] == null ? new Date() : fb.getFechaFabricacion()[i];
			}
			
			c.setFechaFabExt(fechFab);
			c.setFechaRetExt( fb.getUltimoRetimbrado() );

			String[] numPlaca = new String[ fb.getNumPlaca().length ];
			
			for(int i=0; i<fb.getNumPlaca().length; i++) 
			{
				numPlaca[i] = fb.getNumPlaca()[i] == null ? "" : fb.getNumPlaca()[i].toString();
			}
			
			c.setNumeroPlacaExt(numPlaca);
			c.setFabricanteExt( fb.getFabricante() );
			
			Double[] cant = new Double[ fb.getOrden().length ];
			
			for(int i=0; i<fb.getOrden().length; i++) 
			{
//				cant[i] = fb.getOrden()[i] == null ? 1 : fb.getOrden()[i];
				cant[i] = 1D;
			}
			
			c.setCantidadExt(cant);

			Integer[] agentes = new Integer[fb.getTipo().length];
			
			for(int i = 0; i < fb.getTipo().length; i++) 
			{
				agentes[i] = this.aservice.getOidByDescr( this.teservice.leer( fb.getTipo()[i] ).getTipo() );
			}
			
			c.setAgentesExt(agentes);

			Double[] cap = new Double[ fb.getNumPlaca().length ];
			
			for (int i=0; i<fb.getNumPlaca().length; i++) 
			{
				cap[i] = fb.getKg()[i] == null ? null : fb.getKg()[i].doubleValue();
			}

			c.setCapacidadExt(cap);
			c.setDescrExt( fb.getUbicacion() );
			c.setPruebasExt( fb.getPrueba() );
		} 
		else if (fb.getOidpartetipo() == 2) 
		{
			c.setPrecioExt(fb.getPrecio());
			c.setFechaFabExt(fb.getFechaRetimA());
			c.setFechaRetExt(fb.getFechaRetimB());
			c.setCapacidadExt(new Double[fb.getOrden().length]);
			c.setPruebasExt(new Integer[fb.getOrden().length]);
			
			String[] fab = new String[fb.getOrden().length];
			for (int i = 0; i < fb.getOrden().length; i++) {
				fab[i] = "";
			}
			c.setFabricanteExt(fab);

			Double[] cant = new Double[fb.getOrden().length];
			for (int i = 0; i < fb.getOrden().length; i++) {
				cant[i] = 1D;
			}
			c.setCantidadExt(cant);

			Integer[] agentes = new Integer[fb.getLongMang().length];
			for (int i = 0; i < fb.getTipo().length; i++) {
				String tipo = "BIE " + fb.getLongMang()[i];
				agentes[i] = this.aservice.getOidByDescr(tipo);
			}
			c.setAgentesExt(agentes);
			
			String[] nPlaca = new String[fb.getOrden().length];
			for(int i = 0; i < fb.getTipo().length; i++) {
				nPlaca[i] = fb.getNumSerie()[i];
			}
			c.setNumeroPlacaExt(nPlaca);
			
			c.setDescrExt(fb.getUbicacion());

		} else if (fb.getOidpartetipo() == 5 || fb.getOidpartetipo() == 6) {

			List<Integer> ordenCentral = new ArrayList<>();
			List<Integer> ordenDetectores = new ArrayList<>();
			List<Integer> ordenPulsadores = new ArrayList<>();
			List<Integer> ordenSirenas = new ArrayList<>();
			List<Integer> ordenEquipoAuxilar = new ArrayList<>();
			
			Integer numLineas = 0;
			Double[] cant = new Double[0];
			Integer[] agentes = new Integer[0];
			Double[] precios = new Double[0];
			String[] numPlaca = new String[0];
			String[] marca = new String[0];
			String[] ubicacion = new String[0];

			if (fb.getOidpartetipo() == 5) {
				ordenCentral = Arrays.asList(fb.getOrdenCentral()).stream().filter(x -> x != null && x > 0)
						.collect(Collectors.toList());
				ordenDetectores = Arrays.asList(fb.getOrdenDetectores()).stream().filter(x -> x != null && x > 0)
						.collect(Collectors.toList());
				ordenPulsadores = Arrays.asList(fb.getOrdenPulsadores()).stream().filter(x -> x != null && x > 0)
						.collect(Collectors.toList());
				ordenSirenas = Arrays.asList(fb.getOrdenSirenas()).stream().filter(x -> x != null && x > 0)
						.collect(Collectors.toList());
				
				numLineas = ordenCentral.stream().filter(x -> x > 0).collect(Collectors.toList()).size() 
						+ ordenDetectores.stream().filter(x -> x > 0).collect(Collectors.toList()).size()
						+ ordenPulsadores.stream().filter(x -> x > 0).collect(Collectors.toList()).size() 
						+ ordenSirenas.stream().filter(x -> x > 0).collect(Collectors.toList()).size();
				
				List<Integer> cantAux = new ArrayList<>();
				List<Integer> agentesAux = new ArrayList<>();
				List<Double> preciosAux = new ArrayList<>();
				List<String> numPlacaAux = new ArrayList<>();
				List<String> marcaAux = new ArrayList<>();
				List<String> ubicacionAux = new ArrayList<>();
				
				if(ordenCentral.size() > 0) {
					cantAux.add(ordenCentral.stream().filter(x -> x > 0).collect(Collectors.toList()).size());
					agentesAux.add(this.aservice.getOidByDescr("Central de incendio"));
					preciosAux.add(Arrays.asList(fb.getPrecioCentral()).stream().filter(x -> x != null).reduce(.0, Double::sum));
					Collections.addAll(numPlacaAux, fb.getTipoCentral());
					Collections.addAll(marcaAux, fb.getMarca());
					Collections.addAll(ubicacionAux, new String[1]);
				}
				
				if(ordenDetectores.size() > 0) {
					cantAux.add(ordenDetectores.stream().filter(x -> x > 0).collect(Collectors.toList()).size());
					agentesAux.add(this.aservice.getOidByDescr("Detector"));
					preciosAux.add(Arrays.asList(fb.getPrecioDetectores()).stream().filter(x -> x != null).reduce(.0, Double::sum));
					Collections.addAll(numPlacaAux, fb.getFuncionDetectores());
					Collections.addAll(marcaAux, fb.getMarcaDetectores());
					Collections.addAll(ubicacionAux, fb.getUbiDetectores());
					
				}
				
				if(ordenPulsadores.size() > 0) {
					cantAux.add(ordenPulsadores.stream().filter(x -> x > 0).collect(Collectors.toList()).size());
					agentesAux.add(this.aservice.getOidByDescr("Pulsador"));
					preciosAux.add(Arrays.asList(fb.getPrecioPulsadores()).stream().filter(x -> x != null).reduce(.0, Double::sum));
					Collections.addAll(numPlacaAux, fb.getTipoPulsadores());
					Collections.addAll(marcaAux, fb.getMarcaPulsadores());
					Collections.addAll(ubicacionAux, fb.getUbiPulsadores());
				}
				
				if(ordenSirenas.size() > 0) {
					cantAux.add(ordenSirenas.stream().filter(x -> x > 0).collect(Collectors.toList()).size());
					agentesAux.add(this.aservice.getOidByDescr("Sirena"));
					preciosAux.add(Arrays.asList(fb.getPrecioSirenas()).stream().filter(x -> x != null).reduce(.0, Double::sum));
					Collections.addAll(numPlacaAux, fb.getTipoSirenas());
					Collections.addAll(marcaAux, fb.getMarcaSirenas());
					Collections.addAll(ubicacionAux, fb.getUbiSirenas());
				}
				
				cant = cantAux.toArray(new Double[0]);
				agentes = agentesAux.toArray(new Integer[0]);
				precios = preciosAux.toArray(new Double[0]);
				numPlaca = numPlacaAux.toArray(new String[0]);
				marca = marcaAux.toArray(new String[0]);
				ubicacion = ubicacionAux.toArray(new String[0]);
				
				
			} else if (fb.getOidpartetipo() == 6) {
				ordenEquipoAuxilar = Arrays.asList(fb.getOrdenEquipoAuxiliar()).stream().filter(x -> x != null && x > 0)
						.collect(Collectors.toList());
				
				numLineas = ordenEquipoAuxilar.size();
				
				cant = new Double[] { ordenEquipoAuxilar.size() + 0D };
				
				agentes = new Integer[] { this.aservice.getOidByDescr("Equipo auxiliar") };
				
				precios = new Double[] { Arrays.asList(fb.getPrecioEquipoAuxiliar()).stream().reduce(.0, Double::sum) };
			}


			Double[] doubleArray = new Double[numLineas];
			Date[] dateArray = new Date[numLineas];
			String[] stringArray = new String[numLineas];
			Integer[] integerArray = new Integer[numLineas];

			for (int i = 0; i < numLineas; i++) {
				doubleArray[i] = .0d;
				dateArray[i] = new Date();
				stringArray[i] = "";
				integerArray[i] = 0;
			}

			c.setFechaFabExt(dateArray);
			c.setFechaRetExt(dateArray);
			c.setNumeroPlacaExt(numPlaca);
			c.setFabricanteExt(marca);
			c.setCapacidadExt(doubleArray);
			c.setDescrExt(ubicacion);
			c.setPruebasExt(integerArray);

			c.setPrecioExt(precios);
			c.setCantidadExt(cant);
			c.setAgentesExt(agentes);

		}

		c.setPreguntas(fb.getPreguntas());

		return c;
	}

	private void copy(PartesFb fb, TParte tc) 
	{
		tc.setTCliente(this.kdao.read(fb.getOidcliente()));
		tc.setTUsuario(this.udao.get(fb.getOidusuario()));
		tc.setTParteTipo(this.ptdao.get(fb.getOidpartetipo()));
		tc.setFecha(fb.getFecha());
		tc.setNumero(fb.getNumero());
		tc.setDni(fb.getDni());
		tc.setObservaciones(fb.getObservaciones());
		tc.setEstado(this.epdao.get(fb.getOidestadoparte()));
		tc.setEstado2(this.edao.get( fb.getOidestado() == null ? 1 : fb.getOidestado() ));
		tc.setCcEmail(fb.getCcemail());
		tc.setAnterior( fb.getAnterior() );
		tc.setCmto( fb.getCmto() );
		tc.setDirtra( fb.getDirtra() );
		tc.setTParteModalidad( this.pmdao.get( fb.getOidmodalidad() ) );
		tc.setTPartePeriodicidad( this.ppdao.get( fb.getOidperiodicidad() ) );

		if (fb.getOidpartetipo().equals(5)) {

			for (int i = 0; i < fb.getOrdenCentral().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenCentral(fb.getOrdenCentral()[i]);
				l.setMarca(fb.getMarca()[i]);
				l.setPilotos(fb.getPilotos()[i]);
				l.setTipoBateriaCentral(fb.getTipoBateriaCentral()[i]);
				l.setTipoCentral(fb.getTipoCentral()[i]);
				l.setModelo(fb.getModelo()[i]);
				l.setReles(fb.getReles()[i]);
				l.setUnidades(fb.getUnidades()[i]);
				l.setnZona(fb.getNZona()[i]);
				l.setZumbador(fb.getZumbador()[i]);
				l.setZonas(fb.getZonas()[i]);
				l.setMandosControl(fb.getMandosControl()[i]);
				l.setLineasCableado(fb.getLineasCableado()[i]);
				l.setZonasReserva(fb.getZonasReserva()[i]);
				l.setRedIndep(fb.getRedIndep()[i]);
				l.setTemporizador(fb.getTemporizador()[i]);
				l.setEstadoCargaCentral(fb.getEstadoCargaCentral()[i]);
				l.setPrecioCentral(fb.getPrecioCentral()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenFuente().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenFuente(fb.getOrdenFuente()[i]);
				l.setMarchar(fb.getMarchar()[i]);
				// l.setModeloFuente(fb.getModeloFuente()[i]);
				// l.setCorriente(fb.getCorriente()[i]);
				l.setTipoBateriaFuente(fb.getTipoBateriaFuente()[i]);
				l.setEstadoCargaFuente(fb.getEstadoCargaFuente()[i]);
				l.setPrecioFuente(fb.getPrecioFuente()[i]);
				l.setCantidadLuminaria(fb.getCantidadLuminaria()[i]);
				l.setUbicacionLuminaria(fb.getUbicacionLuminaria()[i]);
				l.setAnoLuminaria(fb.getAnoLuminaria()[i]);
				l.setLumenes(fb.getLumenes()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenDetectores().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenDetectores(fb.getOrdenDetectores()[i]);
				l.setUbiDetectores(fb.getUbiDetectores()[i]);
				l.setTipoDetectores(fb.getTipoDetectores()[i]);
				l.setMarcaDetectores(fb.getMarcaDetectores()[i]);
				l.setAnoDetectores(fb.getAnoDetectores()[i]);
				l.setZonaDetectores(fb.getZonaDetectores()[i]);
				l.setEstadoDetectores(fb.getEstadoDetectores()[i]);
				l.setFuncionDetectores(fb.getFuncionDetectores()[i]);
				l.setPrecioDetectores(fb.getPrecioDetectores()[i]);
				l.setCantidadDetectores(fb.getCantidadDetectores()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenPulsadores().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenPulsadores(fb.getOrdenPulsadores()[i]);
				l.setCantidadPulsadores(fb.getCantidadPulsadores()[i]);
				l.setUbiPulsadores(fb.getUbiPulsadores()[i]);
				l.setTipoPulsadores(fb.getTipoPulsadores()[i]);
				l.setMarcaPulsadores(fb.getMarcaPulsadores()[i]);
				l.setAnoPulsadores(fb.getAnoPulsadores()[i]);
				l.setZonaPulsadores(fb.getZonaPulsadores()[i]);
				l.setEstadoPulsadores(fb.getEstadoPulsadores()[i]);
				l.setPrecioPulsadores(fb.getPrecioPulsadores()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenSirenas().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenSirenas(fb.getOrdenSirenas()[i]);
				l.setUbiSirenas(fb.getUbiSirenas()[i]);
				l.setCantidadSirenas(fb.getCantidadSirenas()[i]);
				l.setTipoSirenas(fb.getTipoSirenas()[i]);
				l.setMarcaSirenas(fb.getMarcaSirenas()[i]);
				l.setAnoSirenas(fb.getAnoSirenas()[i]);
				l.setZonaSirenas(fb.getZonaSirenas()[i]);
				l.setEstadoSirenas(fb.getEstadoSirenas()[i]);
				l.setPrecioSirenas(fb.getPrecioSirenas()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

		} else if (fb.getOidpartetipo().equals(6)) {

			for (int i = 0; i < fb.getOrdenEquipoAuxiliar().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenEquipoAuxiliar(fb.getOrdenEquipoAuxiliar()[i]);
				l.setCantidadEquipoAuxiliar(fb.getCantidadEquipoAuxiliar()[i]);
				l.setUbicacionEquipoAuxiliar(fb.getUbicacionEquipoAuxiliar()[i]);
				l.setTipoEquipoAuxiliar(fb.getTipoEquipoAuxiliar()[i]);
				l.setMarcaEquipoAuxiliar(fb.getMarcaEquipoAuxiliar()[i]);
				l.setAnoEquipoAuxiliar(fb.getAnoEquipoAuxiliar()[i]);
				l.setZonaEquipoAuxiliar(fb.getZonaEquipoAuxiliar()[i]);
				l.setEstadoEquipoAuxiliar(fb.getEstadoEquipoAuxiliar()[i]);
				l.setPrecioEquipoAuxiliar(fb.getPrecioEquipoAuxiliar()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenRetenedor().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenRetenedor(fb.getOrdenRetenedor()[i]);
				l.setCantidadRetenedor(fb.getCantidadRetenedor()[i]);
				l.setUbiRetenedor(fb.getUbiRetenedor()[i]);
				l.setMarcaRetenedor(fb.getMarcaRetenedor()[i]);
				l.setAnoRetenedor(fb.getAnoRetenedor()[i]);
				l.setEstadoRetenedor(fb.getEstadoRetenedor()[i]);
				l.setPrecioRetenedor(fb.getPrecioRetenedor()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

			for (int i = 0; i < fb.getOrdenPuertas().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenPuertas(fb.getOrdenPuertas()[i]);
				l.setCantidadPuertas(fb.getCantidadPuertas()[i]);
				l.setUbiPuertas(fb.getUbiPuertas()[i]);
				l.setMarcaPuertas(fb.getMarcaPuertas()[i]);
				l.setAnoPuertas(fb.getAnoPuertas()[i]);
				l.setEstadoPuertas(fb.getEstadoPuertas()[i]);
				l.setPrecioPuertas(fb.getPrecioPuertas()[i]);

				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

		} else if (fb.getOidpartetipo().equals(2)) {
			
			for(int i = 0; i < fb.getOrden().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrden(fb.getOrden()[i]);
				l.setUbicacion(fb.getUbicacion()[i]);
				l.setTipoBie(this.tbdao.get(fb.getTipo()[i]));
				l.setLongMang(fb.getLongMang()[i]);
				l.setFechaRetimA(fb.getFechaRetimA()[i]);
				l.setFechaRetimB(fb.getFechaRetimB()[i]);
				l.setPresionEstatica(fb.getPresionEstatica()[i]);
				l.setPresionDinamica(fb.getPresionDinamica()[i]);
				l.setManguera(fb.getManguera()[i]);
				l.setLanza(fb.getLanza()[i]);
				l.setValvula(fb.getValvula()[i]);
				l.setManometro(fb.getManometro()[i]);
				l.setCristal(fb.getCristal()[i]);
				l.setSennales(fb.getSennales()[i]);
				l.setEstadoGeneral(fb.getEstadoGeneral()[i]);
				l.setPrecio(fb.getPrecio()[i]);
				l.setNumSerie(fb.getNumSerie()[i]);
				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}
			
			for(int i = 0; i < fb.getOrdenBomba().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrdenBomba(fb.getOrdenBomba()[i]);
				l.setTipoBomba(this.tbbdao.get(fb.getTipoBomba()[i]));
				l.setMarcaBomba(fb.getMarcaBomba()[i]);
				l.setModeloBomba(fb.getModeloBomba()[i]);
				l.setFechaBomba(fb.getFechaBomba()[i]);
				l.setMotorBomba(fb.getMotorBomba()[i]);
				l.setVoltajeBomba(fb.getVoltajeBomba()[i]);
				l.setRpmBomba(fb.getRpmBomba()[i]);
				l.setManometroBomba(fb.getManometroBomba()[i]);
				l.setEsferaBomba(fb.getEsferaBomba()[i]);
				l.setValvulasBomba(fb.getValvulasBomba()[i]);
				l.setSaltosBomba(fb.getSaltosBomba()[i]);
				l.setFusiblesBomba(fb.getFusiblesBomba()[i]);
				l.setAlarmaBomba(fb.getAlarmaBomba()[i]);
				l.setCaudalimetroBomba(fb.getCaudalimetroBomba()[i]);
				l.setPresionBomba(fb.getPresionBomba()[i]);
				l.setTParte(tc);
				tc.getTParteLineas().add(l);
			}

		} else {

			for (int i = 0; i < fb.getOrden().length; i++) {
				TParteLinea l = new TParteLinea();

				l.setOrden(fb.getOrden()[i]);

				if (fb.getOidpartetipo().equals(1)) {

					l.setUbicacion(fb.getUbicacion()[i]);
					l.setTipo(this.tedao.get(fb.getTipo()[i]));
					l.setNumPlaca(fb.getNumPlaca()[i]);
					l.setKg(fb.getKg()[i]);
					l.setFabricante(fb.getFabricante()[i]);
					l.setFechaFabricacion(fb.getFechaFabricacion()[i]);
					l.setUltimoRetimbrado(fb.getUltimoRetimbrado()[i]);
					l.setCartel(fb.getCartel()[i]);
					l.setAltura(fb.getAltura()[i]);
					l.setPrecio(fb.getPrecio()[i]);
					this.setPrueba(fb.getPrueba()[i], l);

				} else if (fb.getOidpartetipo().equals(4)) {

					l.setCantidad(fb.getCantidad()[i]);
					l.setPrecio(fb.getPrecio()[i]);
					l.setDescripcion(fb.getDescripcion()[i]);

				}

				l.setTParte(tc);
				tc.getTParteLineas().add(l);

			}
		}

		if (fb.getPreguntas() != null) {
			for (String p : fb.getPreguntas()) {
				String[] partes = p.split("_");
				int oidpregunta = Integer.valueOf(partes[0]);
				int oidrespuesta = Integer.valueOf(partes[1]);

				TPreguntasParte tpp = new TPreguntasParte();

				tpp.setTParte(tc);
				tpp.setTPreguntas(this.qdao.getQuestion(oidpregunta));
				tpp.setTPreguntasRespuestas(this.qdao.getAswer(oidrespuesta));

				tc.getTPreguntasParte().add(tpp);
			}
		}
	}

	private void setPrueba(Integer prueba, TParteLinea p) {

		switch (prueba) {
		case 1:
			p.setRc(false);
			p.setRt(true);
			p.setRv(false);
			p.setNuevo(false);
			break;
		case 2:
			p.setRc(true);
			p.setRt(false);
			p.setRv(false);
			p.setNuevo(false);
			break;
		case 3:
			p.setRc(false);
			p.setRt(false);
			p.setRv(true);
			p.setNuevo(false);
			break;
		case 4:
			p.setRc(false);
			p.setRt(false);
			p.setRv(false);
			p.setNuevo(true);
			break;

		default:
			p.setRc(false);
			p.setRt(false);
			p.setRv(false);
			p.setNuevo(false);
			break;
		}

	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT)
	public TParte actualizar(PartesFb fb) throws IllegalAccessException, InvocationTargetException {
		// Crear el objeto ...

		TParte tp = this.pdao.read(fb.getOid());

		for (TParteLinea l : tp.getTParteLineas()) {
			this.pldao.delete(l);
		}

		tp.getTParteLineas().clear();

		for (TPreguntasParte p : tp.getTPreguntasParte()) {
			this.qdao.delete(p);
		}

		tp.getTPreguntasParte().clear();

		this.copy(fb, tp);

		tp.setAuditoria2(new Date());

		this.pdao.update(tp);

		// Actualizamos el log ...

		this.pcdao.create(tp);

		return tp;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT)
	public void borrar(int u) throws IllegalAccessException, InvocationTargetException {
		this.pdao.delete(u);
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT)
	public void actualizaAuditoriaEmail(int oid) throws IllegalAccessException, InvocationTargetException {
		TParte tp = this.pdao.read(oid);
		tp.setAuditoriaEmail(new Date());

		this.pdao.update(tp);
	}
	
	/**
	 * Tiene que salir el botón "PDF Contrato" para un parte, si ...
	 * + Existe un matrimonio para el parte
	 * + El matrimonio tiene un contrato
	 * @param p
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public boolean isPaseChapuza(TParte p) 
	throws IllegalAccessException, InvocationTargetException 
	{
//		List<TParte> pa = new ArrayList<>();
//		pa.add(p);	
//		return this.mservice.leer(pa).size() > 0;
		
		List<TParte> pa 		= Arrays.asList(p);
		List<TMatrimonio> ma 	= this.mservice.leer(pa);
		boolean chapuza			= !ma.isEmpty() && ma.get(0).getContrato() != null;
		
		return chapuza;
	}
}
