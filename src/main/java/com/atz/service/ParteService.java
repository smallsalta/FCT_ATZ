package com.atz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.atz.dao.ClienteDAO;
import com.atz.dao.EstadoParteDAO;
import com.atz.dao.ParteCambiosDAO;
import com.atz.dao.ParteDAO;
import com.atz.dao.ParteLineaDAO;
import com.atz.dao.ParteTipoDAO;
import com.atz.dao.PreguntasDAO;
import com.atz.dao.TipoBieDAO;
import com.atz.dao.TipoExtintorDAO;
import com.atz.dao.UsuarioDAO;
import com.atz.fb.ContratosFb;
import com.atz.fb.ParteBuscarFb;
import com.atz.fb.PartesFb;
import com.atz.persistencia.TCliente;
import com.atz.persistencia.TContrato;
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
	private EstadoParteDAO epdao;

	@Autowired
	private PreguntasDAO qdao;

	@Autowired
	private ContratoService cservice;

	@Autowired
	private TipoExtintorService teservice;

	@Autowired
	private AgenteService aservice;

	@Transactional(readOnly = true)
	public List<TParte> leerTodos() {
		return this.pdao.readAll();
	}

	@Transactional(readOnly = true)
	public TParte leer(int oid) {
		TParte p = this.pdao.read(oid);

		Hibernate.initialize(p.getTUsuario());
		Hibernate.initialize(p.getTParteTipo());
		Hibernate.initialize(p.getTPreguntasParte());

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
	public TContrato crearContrato(PartesFb fb) 
	throws IllegalAccessException, InvocationTargetException 
	{
		TCliente cl 	= this.kdao.read(fb.getOidcliente());
		ContratosFb c 	= this.crearContrato(fb, cl);
		
		return this.cservice.crear(c);
	}

	private ContratosFb crearContrato(PartesFb fb, TCliente cl) 
	{
		ContratosFb c = new ContratosFb();
		c.init();

		c.setFecha( new Date() );
		c.setOidcliente(cl.getOid());
		c.setOidusuario(fb.getOidusuario());
		c.setDireccion(cl.getDireccion());
		c.setNumero(this.cservice.maxNumero());
		c.setAnexo(fb.getObservaciones());
		c.setTrimestral(1.0d);

		if (fb.getOidpartetipo() == 1) {
			c.setPrecioExt(fb.getPrecio());

			Date[] fechFab = new Date[fb.getFechaFabricacion().length];
			for (int i = 0; i < fb.getFechaFabricacion().length; i++) {
				fechFab[i] = fb.getFechaFabricacion()[i] == null ? new Date() : fb.getFechaFabricacion()[i];
			}
			c.setFechaFabExt(fechFab);
			c.setFechaRetExt(fb.getUltimoRetimbrado());

			String[] numPlaca = new String[fb.getNumPlaca().length];
			for (int i = 0; i < fb.getNumPlaca().length; i++) {
				numPlaca[i] = fb.getNumPlaca()[i] == null ? "" : fb.getNumPlaca()[i].toString();
			}
			c.setNumeroPlacaExt(numPlaca);
			c.setFabricanteExt(fb.getFabricante());
			Integer[] cant = new Integer[fb.getOrden().length];
			for (int i = 0; i < fb.getOrden().length; i++) {
				cant[i] = fb.getOrden()[i] == null ? 1 : fb.getOrden()[i];
			}
			c.setCantidadExt(cant);

			Integer[] agentes = new Integer[fb.getTipo().length];
			for (int i = 0; i < fb.getTipo().length; i++) {
				agentes[i] = this.aservice.getOidByDescr(this.teservice.leer(fb.getTipo()[i]).getTipo());
			}
			c.setAgentesExt(agentes);

			Double[] cap = new Double[fb.getNumPlaca().length];
			for (int i = 0; i < fb.getNumPlaca().length; i++) {
				cap[i] = fb.getKg()[i] == null ? null : fb.getKg()[i].doubleValue();
			}

			c.setCapacidadExt(cap);
			c.setDescrExt(fb.getUbicacion());
			c.setPruebasExt(fb.getPrueba());
		
		} else if(fb.getOidpartetipo() == 2) {
			c.setPrecioExt(fb.getPrecio());
			c.setFechaFabExt(fb.getFechaRetimA());
			c.setFechaRetExt(fb.getFechaRetimB());
			c.setNumeroPlacaExt(new String[fb.getOrden().length]);
			c.setFabricanteExt(new String[fb.getOrden().length]);
			c.setCapacidadExt(new Double[fb.getOrden().length]);
			c.setPruebasExt(new Integer[fb.getOrden().length]);
			
			Integer[] cant = new Integer[fb.getOrden().length];
			for (int i = 0; i < fb.getOrden().length; i++) {
				cant[i] = fb.getOrden()[i] == null ? 1 : fb.getOrden()[i];
			}
			c.setCantidadExt(cant);
			
			Integer[] agentes = new Integer[fb.getLongMang().length];
			for (int i = 0; i < fb.getTipo().length; i++) {
				String tipo = "BIE " + fb.getLongMang()[i];
				agentes[i] = this.aservice.getOidByDescr(tipo);
			}
			c.setAgentesExt(agentes);
			c.setDescrExt(fb.getUbicacion());
			
		} else if (fb.getOidpartetipo() == 5 || fb.getOidpartetipo() == 6) {

			List<Integer> ordenCentral = Arrays.asList(fb.getOrdenCentral()).stream().filter(x -> x != null && x > 0)
					.collect(Collectors.toList());
			List<Integer> ordenFuente = Arrays.asList(fb.getOrdenFuente()).stream().filter(x -> x != null && x > 0)
					.collect(Collectors.toList());
			List<Integer> ordenDetectores = Arrays.asList(fb.getOrdenDetectores()).stream()
					.filter(x -> x != null && x > 0).collect(Collectors.toList());
			List<Integer> ordenPulsadores = Arrays.asList(fb.getOrdenPulsadores()).stream()
					.filter(x -> x != null && x > 0).collect(Collectors.toList());
			List<Integer> ordenSirenas = Arrays.asList(fb.getOrdenSirenas()).stream().filter(x -> x != null && x > 0)
					.collect(Collectors.toList());
			List<Integer> ordenEquipoAuxilar = Arrays.asList(fb.getOrdenEquipoAuxiliar()).stream()
					.filter(x -> x != null && x > 0).collect(Collectors.toList());

			Integer numLineas = ordenCentral.size() + ordenFuente.size() + ordenDetectores.size()
					+ ordenPulsadores.size() + ordenSirenas.size() + ordenEquipoAuxilar.size();

			Integer[] cant = new Integer[] { ordenCentral.size(), ordenFuente.size(), ordenDetectores.size(),
					ordenPulsadores.size(), ordenSirenas.size(), ordenEquipoAuxilar.size() };
			Integer[] agentes = new Integer[] { this.aservice.getOidByDescr("Central de incendio"),
					this.aservice.getOidByDescr("Central de monóxido"), this.aservice.getOidByDescr("Detector"),
					this.aservice.getOidByDescr("Pulsador"), this.aservice.getOidByDescr("Sirena"),
					this.aservice.getOidByDescr("Equipo auxiliar") };

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

			c.setPrecioExt(doubleArray);
			c.setFechaFabExt(dateArray);
			c.setFechaRetExt(dateArray);
			c.setNumeroPlacaExt(stringArray);
			c.setFabricanteExt(stringArray);
			c.setCapacidadExt(doubleArray);
			c.setDescrExt(stringArray);
			c.setPruebasExt(integerArray);

			c.setCantidadExt(cant);
			c.setAgentesExt(agentes);

		}

		c.setPreguntas(fb.getPreguntas());

		return c;
	}

	private void copy(PartesFb fb, TParte tc) {
		tc.setTCliente(this.kdao.read(fb.getOidcliente()));
		tc.setTUsuario(this.udao.get(fb.getOidusuario()));
		tc.setTParteTipo(this.ptdao.get(fb.getOidpartetipo()));
		tc.setFecha(fb.getFecha());
		tc.setNumero(fb.getNumero());
		tc.setDni(fb.getDni());
		tc.setObservaciones(fb.getObservaciones());
		tc.setEstado(this.epdao.get(fb.getOidestadoparte()));
		tc.setCcEmail(fb.getCcemail());

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

				} else if (fb.getOidpartetipo().equals(2)) {

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
}
