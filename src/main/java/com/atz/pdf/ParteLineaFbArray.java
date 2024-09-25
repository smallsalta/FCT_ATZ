package com.atz.pdf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
public class ParteLineaFbArray implements JRDataSource {

	private List<ParteLineaFb> partelineas;
	private ParteLineaFb actual;
	private int siguiente;

	public ParteLineaFbArray() {
		this(new ArrayList<>());
	}

	public ParteLineaFbArray(List<ParteLineaFb> pl) {
		this.partelineas = new ArrayList<>(pl);
		this.actual = null;
		this.siguiente = 0;
	}

	@Override
	public boolean next() throws JRException {
		boolean next = true;

		try {

			this.actual = this.partelineas.get(this.siguiente);
			this.siguiente++;

		} catch (IndexOutOfBoundsException e) {
			next = false;
		}

		return next;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object resp = null;

		switch (jrField.getName()) {
		case "orden":
			resp = this.actual.getOrden();
			break;

		case "ubicacion":
			resp = this.actual.getUbicacion();
			break;

		case "numPlaca":
			resp = this.actual.getNumPlaca();
			break;

		case "tipo":
			resp = this.actual.getTipo();
			break;

		case "kg":
			resp = this.actual.getKg();
			break;

		case "fabricante":
			resp = this.actual.getFabricante();
			break;

		case "fechaFabricacion":
			resp = this.actual.getFechaFabricacion();
			break;

		case "ultimoRetimbrado":
			resp = this.actual.getUltimoRetimbrado();
			break;

		case "rv":
			resp = this.actual.getRv();
			break;

		case "rc":
			resp = this.actual.getRc();
			break;

		case "rt":
			resp = this.actual.getRt();
			break;

		case "nuevo":
			resp = this.actual.getNuevo();
			break;

		case "cartel":
			resp = this.actual.getCartel();
			break;

		case "altura":
			resp = this.actual.getAltura();
			break;

		case "precio":
			resp = this.actual.getPrecio();
			break;

		case "longMang":
			resp = this.actual.getLongMang();
			break;

		case "fechaRetimA":
			resp = this.actual.getFechaRetimA();
			break;

		case "fechaRetimB":
			resp = this.actual.getFechaRetimB();
			break;

		case "presionEstatica":
			resp = this.actual.getPresionEstatica();
			break;

		case "presionDinamica":
			resp = this.actual.getPresionDinamica();
			break;

		case "manguera":
			resp = this.actual.getManguera();
			break;

		case "lanza":
			resp = this.actual.getLanza();
			break;

		case "valvula":
			resp = this.actual.getValvula();
			break;

		case "manometro":
			resp = this.actual.getManometro();
			break;

		case "cristal":
			resp = this.actual.getCristal();
			break;

		case "sennales":
			resp = this.actual.getSennales();
			break;

		case "estadoGeneral":
			resp = this.actual.getEstadoGeneral();
			break;

		case "numSerie":
			resp = this.actual.getNumSerie();
			break;

		case "cantidad":
			resp = this.actual.getCantidad();
			break;

		case "descripcion":
			resp = this.actual.getDescripcion();
			break;

		case "marca":
			resp = this.actual.getMarca();
			break;

		case "pilotos":
			resp = this.actual.getPilotos();
			break;

		case "tipoBateriaCentral":
			resp = this.actual.getTipoBateriaCentral();
			break;
			
		case "tipoCentral":
			resp = this.actual.getTipoCentral();
			break;

		case "modelo":
			resp = this.actual.getModelo();
			break;

		case "reles":
			resp = this.actual.getReles();
			break;

		case "unidades":
			resp = this.actual.getUnidades();
			break;

		case "nZona":
			resp = this.actual.getNZona();
			break;

		case "zumbador":
			resp = this.actual.getZumbador();
			break;

		case "zonas":
			resp = this.actual.getZonas();
			break;

		case "mandosControl":
			resp = this.actual.getMandosControl();
			break;

		case "lineasCableado":
			resp = this.actual.getLineasCableado();
			break;

		case "zonasReserva":
			resp = this.actual.getZonasReserva();
			break;

		case "redIndep":
			resp = this.actual.getRedIndep();
			break;

		case "temporizador":
			resp = this.actual.getTemporizador();
			break;

		case "estadoCargaCentral":
			resp = this.actual.getEstadoCargaCentral();
			break;

		case "precioCentral":
			resp = this.actual.getPrecioCentral();
			break;

		case "marchar":
			resp = this.actual.getMarchar();
			break;

		case "modeloFuente":
			resp = this.actual.getModeloFuente();
			break;

		case "corriente":
			resp = this.actual.getCorriente();
			break;

		case "tipoBateriaFuente":
			resp = this.actual.getTipoBateriaFuente();
			break;

		case "estadoCargaFuente":
			resp = this.actual.getEstadoCargaFuente();
			break;
		case "precioFuente":
			resp = this.actual.getPrecioFuente();
			break;
		case "ubiDetectores":
			resp = this.actual.getUbiDetectores();
			break;

		case "tipoDetectores":
			resp = this.actual.getTipoDetectores();
			break;

		case "marcaDetectores":
			resp = this.actual.getMarcaDetectores();
			break;
			
		case "funcionDetectores":
			resp = this.actual.getFuncionDetectores();
			break;

		case "anoDetectores":
			resp = this.actual.getAnoDetectores();
			break;

		case "zonaDetectores":
			resp = this.actual.getZonaDetectores();
			break;

		case "estadoDetectores":
			resp = this.actual.getEstadoDetectores();
			break;
		case "precioDetectores":
			resp = this.actual.getPrecioDetectores();
			break;
		case "ubiPulsadores":
			resp = this.actual.getUbiPulsadores();
			break;
		case "cantidadPulsadores":
			resp = this.actual.getCantidadPulsadores();
			break;

		case "tipoPulsadores":
			resp = this.actual.getTipoPulsadores();
			break;

		case "marcaPulsadores":
			resp = this.actual.getMarcaPulsadores();
			break;

		case "anoPulsadores":
			resp = this.actual.getAnoPulsadores();
			break;

		case "zonaPulsadores":
			resp = this.actual.getZonaPulsadores();
			break;

		case "estadoPulsadores":
			resp = this.actual.getEstadoPulsadores();
			break;
		case "precioPulsadores":
			resp = this.actual.getPrecioPulsadores();
			break;
		case "ubiSirenas":
			resp = this.actual.getUbiSirenas();
			break;
		case "cantidadSirenas":
			resp = this.actual.getCantidadSirenas();
			break;
		case "tipoSirenas":
			resp = this.actual.getTipoSirenas();
			break;

		case "marcaSirenas":
			resp = this.actual.getMarcaSirenas();
			break;

		case "anoSirenas":
			resp = this.actual.getAnoSirenas();
			break;

		case "zonaSirenas":
			resp = this.actual.getZonaSirenas();
			break;

		case "estadoSirenas":
			resp = this.actual.getEstadoSirenas();
			break;
		case "precioSirenas":
			resp = this.actual.getPrecioSirenas();
			break;
			
		case "cantidadEquipoAuxiliar":
			resp = this.actual.getCantidadEquipoAuxiliar();
			break;
			
		case "ubicacionEquipoAuxiliar":
			resp = this.actual.getUbicacionEquipoAuxiliar();
			break;
		case "tipoEquipoAuxiliar":
			resp = this.actual.getTipoEquipoAuxiliar();
			break;
		case "marcaEquipoAuxiliar":
			resp = this.actual.getMarcaEquipoAuxiliar();
			break;
		case "anoEquipoAuxiliar":
			resp = this.actual.getAnoEquipoAuxiliar();
			break;
		case "zonaEquipoAuxiliar":
			resp = this.actual.getZonaEquipoAuxiliar();
			break;
		case "estadoEquipoAuxiliar":
			resp = this.actual.getEstadoEquipoAuxiliar();
			break;
		case "precioEquipoAuxiliar":
			resp = this.actual.getPrecioEquipoAuxiliar();
			break;
		case "cantidadRetenedor":
			resp = this.actual.getCantidadRetenedor();
			break;
		case "ubiRetenedor":
			resp = this.actual.getUbiRetenedor();
			break;
		case "marcaRetenedor":
			resp = this.actual.getMarcaRetenedor();
			break;
		case "anoRetenedor":
			resp = this.actual.getAnoRetenedor();
			break;
		case "estadoRetenedor":
			resp = this.actual.getEstadoRetenedor();
			break;
		case "precioRetenedor":
			resp = this.actual.getPrecioRetenedor();
			break;
		case "cantidadPuertas":
			resp = this.actual.getCantidadPuertas();
			break;
		case "ubiPuertas":
			resp = this.actual.getUbiPuertas();
			break;
		case "marcaPuertas":
			resp = this.actual.getMarcaPuertas();
			break;
		case "anoPuertas":
			resp = this.actual.getAnoPuertas();
			break;
		case "estadoPuertas":
			resp = this.actual.getEstadoPuertas();
			break;
		case "precioPuertas":
			resp = this.actual.getPrecioPuertas();
			break;
		case "cantidadLuminaria":
			resp = this.actual.getCantidadLuminaria();
			break;
		case "ubicacionLuminaria":
			resp = this.actual.getUbicacionLuminaria();
			break;
		case "anoLuminaria":
			resp = this.actual.getAnoLuminaria();
			break;
		case "lumenes":
			resp = this.actual.getLumenes();
			break;
		case "cantidadDetectores":
			resp = this.actual.getCantidadDetectores();
			break;
		case "ordenBomba":
			resp = this.actual.getOrdenBomba();
			break;
		case "tipoBomba":
			resp = this.actual.getTipoBomba();
			break;
		case "marcaBomba":
			resp = this.actual.getMarcaBomba();
			break;
		case "modeloBomba":
			resp = this.actual.getModeloBomba();
			break;
		case "fechaBomba":
			resp = this.actual.getFechaBomba() != null ? new SimpleDateFormat("dd/MM/yyyy").format( this.actual.getFechaBomba() ) : "";
			break;
		case "motorBomba":
			resp = this.actual.getMotorBomba();
			break;
		case "voltajeBomba":
			resp = this.actual.getVoltajeBomba();
			break;
		case "rpmBomba":
			resp = this.actual.getRpmBomba();
			break;
		case "manometroBomba":
			resp = this.actual.getManometroBomba();
			break;
		case "esferaBomba":
			resp = this.actual.getEsferaBomba();
			break;
		case "valvulasBomba":
			resp = this.actual.getValvulasBomba();
			break;
		case "saltosBomba":
			resp = this.actual.getSaltosBomba();
			break;
		case "fusiblesBomba":
			resp = this.actual.getFusiblesBomba();
			break;
		case "alarmaBomba":
			resp = this.actual.getAlarmaBomba();
			break;
		case "caudalimetroBomba":
			resp = this.actual.getCaudalimetroBomba();
			break;
		case "presionBomba":
			resp = this.actual.getPresionBomba();
			break;
		default:
			break;
		}

		return resp;
	}

}
