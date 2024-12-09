package com.atz.persistencia;
// Generated 14 may 2023 9:28:57 by Hibernate Tools 4.3.6.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TParteLinea generated by hbm2java
 */
@Entity
@Table(name = "t_parte_linea", schema = "public")
public class TParteLinea implements java.io.Serializable {

	private Integer oid;
	private TParte TParte;
	private Integer orden;
	
	private String ubicacion;
	private String numPlaca;
	private TTipoExtintor tipo;
	private Integer kg;
	private String fabricante;
	private Date fechaFabricacion;
	private Date ultimoRetimbrado;
	private Boolean rv;
	private Boolean rc;
	private Boolean rt;
	private Boolean nuevo;
	private Date cartel;
	private String altura;
	private Double precio;
	
	private String longMang;
	private Date fechaRetimA;
	private Date fechaRetimB;
	private Double presionEstatica;
	private Double presionDinamica;
	private String manguera;
	private String lanza;
	private String valvula;
	private String manometro;
	private String cristal;
	private String sennales;
	private String estadoGeneral;
	private TTipoBie tipoBie;
	private String numSerie;
	
	private Integer ordenBomba;
	private TTipoBieBomba tipoBomba;
	private String marcaBomba;
	private String modeloBomba;
	private Date fechaBomba;
	private String motorBomba;
	private String voltajeBomba;
	private Double rpmBomba;
	private String manometroBomba;
	private String esferaBomba;
	private String valvulasBomba;
	private String saltosBomba;
	private String fusiblesBomba;
	private String alarmaBomba;
	private String caudalimetroBomba;
	private Double presionBomba;
	
	private Integer cantidad;
	private String descripcion;
	
	private String marca;
    private String pilotos;
    private String tipoBateriaCentral;
    private String tipoCentral;
    private String modelo;
    private String reles;
    private String unidades;
    private String nZona;
    private String zumbador;
    private String zonas;
    private String mandosControl;
    private String lineasCableado;
    private String zonasReserva;
    private String redIndep;
    private String temporizador;
    private String estadoCargaCentral;
    
    private String marchar;
    private String modeloFuente;
    private String corriente;
    private String tipoBateriaFuente;
    private String estadoCargaFuente;
    
    private String ubiDetectores;
    private String tipoDetectores;
    private String marcaDetectores;
    private String anoDetectores;
    private String zonaDetectores;
    private String estadoDetectores;
    private String funcionDetectores;
    private String cantidadDetectores;
    
    private String ubiPulsadores;
    private String cantidadPulsadores;
    private String tipoPulsadores;
    private String marcaPulsadores;
    private String anoPulsadores;
    private String zonaPulsadores;
    private String estadoPulsadores;
    
    private String ubiSirenas;
    private String cantidadSirenas;
    private String tipoSirenas;
    private String marcaSirenas;
    private String anoSirenas;
    private String zonaSirenas;
    private String estadoSirenas;
    
    private String cantidadEquipoAuxiliar;
    private String ubicacionEquipoAuxiliar;
    private String tipoEquipoAuxiliar;
    private String marcaEquipoAuxiliar;
    private String anoEquipoAuxiliar;
    private String zonaEquipoAuxiliar;
    private String estadoEquipoAuxiliar;
    
    private String cantidadRetenedor;
    private String ubiRetenedor;
    private String marcaRetenedor;
    private String anoRetenedor;
    private String estadoRetenedor;
    
    private String cantidadPuertas;
    private String ubiPuertas;
    private String marcaPuertas;
    private String anoPuertas;
    private String estadoPuertas;
    
    private Integer ordenCentral;
    private Integer ordenFuente;
    private Integer ordenDetectores;
    private Integer ordenPulsadores;
    private Integer ordenSirenas;
    private Integer ordenEquipoAuxiliar;
    private Integer ordenRetenedor;
    private Integer ordenPuertas;
    
    private Double precioCentral;
    private Double precioFuente;
    private Double precioDetectores;
    private Double precioPulsadores;
    private Double precioSirenas;
    private Double precioEquipoAuxiliar;
    private Double precioRetenedor;
    private Double precioPuertas;
    
    private String cantidadLuminaria;
    private String ubicacionLuminaria;
    private String anoLuminaria;
    private String lumenes;

	public TParteLinea() {
		
	}

	public TParteLinea(Integer oid) {
		this.oid = oid;
	}

	public TParteLinea(Integer oid, TParte TParte, Integer orden) {
		this.oid = oid;
		this.TParte = TParte;
		this.orden = orden;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "oid", unique = true, nullable = false)
	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tparte")
	public TParte getTParte() {
		return this.TParte;
	}

	public void setTParte(TParte TParte) {
		this.TParte = TParte;
	}

	@Column(name = "orden")
	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@Column(name = "ubicacion")
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Column(name = "n_placa")
	public String getNumPlaca() {
		return numPlaca;
	}

	public void setNumPlaca(String nPlaca) {
		this.numPlaca = nPlaca;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo")
	public TTipoExtintor getTipo() {
		return tipo;
	}

	public void setTipo(TTipoExtintor tipo) {
		this.tipo = tipo;
	}

	@Column(name = "kg")
	public Integer getKg() {
		return kg;
	}

	public void setKg(Integer kg) {
		this.kg = kg;
	}

	@Column(name = "fabricante")
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fabricacion")
	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ultimo_retimbrado")
	public Date getUltimoRetimbrado() {
		return ultimoRetimbrado;
	}

	public void setUltimoRetimbrado(Date ultimoRetimbrado) {
		this.ultimoRetimbrado = ultimoRetimbrado;
	}

	@Column(name = "rv")
	public Boolean getRv() {
		return rv;
	}

	public void setRv(Boolean rv) {
		this.rv = rv;
	}

	@Column(name = "rc")
	public Boolean getRc() {
		return rc;
	}

	public void setRc(Boolean rc) {
		this.rc = rc;
	}

	@Column(name = "rt")
	public Boolean getRt() {
		return rt;
	}

	public void setRt(Boolean rt) {
		this.rt = rt;
	}

	@Column(name = "nuevo")
	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cartel")
	public Date getCartel() {
		return cartel;
	}

	public void setCartel(Date cartel) {
		this.cartel = cartel;
	}

	@Column(name = "altura")
	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	@Column(name = "precio", precision = 17, scale = 17)
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Column(name = "long_mang")
	public String getLongMang() {
		return longMang;
	}

	public void setLongMang(String longMang) {
		this.longMang = longMang;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_retim_a")
	public Date getFechaRetimA() {
		return fechaRetimA;
	}

	public void setFechaRetimA(Date fechaRetimA) {
		this.fechaRetimA = fechaRetimA;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_retim_b")
	public Date getFechaRetimB() {
		return fechaRetimB;
	}

	public void setFechaRetimB(Date fechaRetimB) {
		this.fechaRetimB = fechaRetimB;
	}

	@Column(name = "presion_estatica", precision = 17, scale = 17)
	public Double getPresionEstatica() {
		return presionEstatica;
	}

	public void setPresionEstatica(Double presionEstatica) {
		this.presionEstatica = presionEstatica;
	}

	@Column(name = "presion_dinamica", precision = 17, scale = 17)
	public Double getPresionDinamica() {
		return presionDinamica;
	}

	public void setPresionDinamica(Double presionDinamica) {
		this.presionDinamica = presionDinamica;
	}

	@Column(name = "manguera")
	public String getManguera() {
		return manguera;
	}

	public void setManguera(String manguera) {
		this.manguera = manguera;
	}

	@Column(name = "lanza")
	public String getLanza() {
		return lanza;
	}

	public void setLanza(String lanza) {
		this.lanza = lanza;
	}

	@Column(name = "valvula")
	public String getValvula() {
		return valvula;
	}

	public void setValvula(String valvula) {
		this.valvula = valvula;
	}

	@Column(name = "manometro")
	public String getManometro() {
		return manometro;
	}

	public void setManometro(String manometro) {
		this.manometro = manometro;
	}

	@Column(name = "cristal")
	public String getCristal() {
		return cristal;
	}

	public void setCristal(String cristal) {
		this.cristal = cristal;
	}

	@Column(name = "sennales")
	public String getSennales() {
		return sennales;
	}

	public void setSennales(String sennales) {
		this.sennales = sennales;
	}

	@Column(name = "estado_general")
	public String getEstadoGeneral() {
		return estadoGeneral;
	}

	public void setEstadoGeneral(String estadoGeneral) {
		this.estadoGeneral = estadoGeneral;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_bie")
	public TTipoBie getTipoBie() {
		return tipoBie;
	}

	public void setTipoBie(TTipoBie tipoBie) {
		this.tipoBie = tipoBie;
	}
	
	@Column(name = "num_serie")
	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	@Column(name = "orden_bomba")
	public Integer getOrdenBomba() {
		return ordenBomba;
	}

	public void setOrdenBomba(Integer ordenBomba) {
		this.ordenBomba = ordenBomba;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_bomba")
	public TTipoBieBomba getTipoBomba() {
		return tipoBomba;
	}

	public void setTipoBomba(TTipoBieBomba tipoBomba) {
		this.tipoBomba = tipoBomba;
	}

	@Column(name = "marca_bomba")
	public String getMarcaBomba() {
		return marcaBomba;
	}

	public void setMarcaBomba(String marcaBomba) {
		this.marcaBomba = marcaBomba;
	}

	@Column(name = "modelo_bomba")
	public String getModeloBomba() {
		return modeloBomba;
	}

	public void setModeloBomba(String modeloBomba) {
		this.modeloBomba = modeloBomba;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_bomba")
	public Date getFechaBomba() {
		return fechaBomba;
	}

	public void setFechaBomba(Date fechaBomba) {
		this.fechaBomba = fechaBomba;
	}

	@Column(name = "motor_bomba")
	public String getMotorBomba() {
		return motorBomba;
	}

	public void setMotorBomba(String motorBomba) {
		this.motorBomba = motorBomba;
	}

	@Column(name = "voltaje_bomba")
	public String getVoltajeBomba() {
		return voltajeBomba;
	}

	public void setVoltajeBomba(String voltajeBomba) {
		this.voltajeBomba = voltajeBomba;
	}

	@Column(name = "rpm_bomba", precision = 17, scale = 17)
	public Double getRpmBomba() {
		return rpmBomba;
	}

	public void setRpmBomba(Double rpmBomba) {
		this.rpmBomba = rpmBomba;
	}

	@Column(name = "manometro_bomba")
	public String getManometroBomba() {
		return manometroBomba;
	}

	public void setManometroBomba(String manometroBomba) {
		this.manometroBomba = manometroBomba;
	}

	@Column(name = "esfera_bomba")
	public String getEsferaBomba() {
		return esferaBomba;
	}

	public void setEsferaBomba(String esferaBomba) {
		this.esferaBomba = esferaBomba;
	}

	@Column(name = "valvulas_bomba")
	public String getValvulasBomba() {
		return valvulasBomba;
	}

	public void setValvulasBomba(String valvulasBomba) {
		this.valvulasBomba = valvulasBomba;
	}

	@Column(name = "saltos_bomba")
	public String getSaltosBomba() {
		return saltosBomba;
	}

	public void setSaltosBomba(String saltosBomba) {
		this.saltosBomba = saltosBomba;
	}

	@Column(name = "fusibles_bomba")
	public String getFusiblesBomba() {
		return fusiblesBomba;
	}

	public void setFusiblesBomba(String fusiblesBomba) {
		this.fusiblesBomba = fusiblesBomba;
	}

	@Column(name = "alarma_bomba")
	public String getAlarmaBomba() {
		return alarmaBomba;
	}

	public void setAlarmaBomba(String alarmaBomba) {
		this.alarmaBomba = alarmaBomba;
	}

	@Column(name = "caudalimetro_bomba")
	public String getCaudalimetroBomba() {
		return caudalimetroBomba;
	}

	public void setCaudalimetroBomba(String caudalimetroBomba) {
		this.caudalimetroBomba = caudalimetroBomba;
	}

	@Column(name = "presion_bomba", precision = 17, scale = 17)
	public Double getPresionBomba() {
		return presionBomba;
	}

	public void setPresionBomba(Double presionBomba) {
		this.presionBomba = presionBomba;
	}

	@Column(name = "cantidad")
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "marca")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name = "pilotos")
	public String getPilotos() {
		return pilotos;
	}

	public void setPilotos(String pilotos) {
		this.pilotos = pilotos;
	}

	@Column(name = "tipo_bateria_central")
	public String getTipoBateriaCentral() {
		return tipoBateriaCentral;
	}

	public void setTipoBateriaCentral(String tipoBateriaCentral) {
		this.tipoBateriaCentral = tipoBateriaCentral;
	}
	
	@Column(name = "tipo_central")
	public String getTipoCentral() {
		return tipoCentral;
	}

	public void setTipoCentral(String tipoCentral) {
		this.tipoCentral = tipoCentral;
	}

	@Column(name = "modelo")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "reles")
	public String getReles() {
		return reles;
	}

	public void setReles(String reles) {
		this.reles = reles;
	}

	@Column(name = "unidades")
	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	@Column(name = "n_zona")
	public String getnZona() {
		return nZona;
	}

	public void setnZona(String nZona) {
		this.nZona = nZona;
	}

	@Column(name = "zumbador")
	public String getZumbador() {
		return zumbador;
	}

	public void setZumbador(String zumbador) {
		this.zumbador = zumbador;
	}

	@Column(name = "zonas")
	public String getZonas() {
		return zonas;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	@Column(name = "mandos_control")
	public String getMandosControl() {
		return mandosControl;
	}

	public void setMandosControl(String mandosControl) {
		this.mandosControl = mandosControl;
	}

	@Column(name = "lineas_cableado")
	public String getLineasCableado() {
		return lineasCableado;
	}

	public void setLineasCableado(String lineasCableado) {
		this.lineasCableado = lineasCableado;
	}

	@Column(name = "zonas_reserva")
	public String getZonasReserva() {
		return zonasReserva;
	}

	public void setZonasReserva(String zonasReserva) {
		this.zonasReserva = zonasReserva;
	}

	@Column(name = "red_indep")
	public String getRedIndep() {
		return redIndep;
	}

	public void setRedIndep(String redIndep) {
		this.redIndep = redIndep;
	}

	@Column(name = "temporizador")
	public String getTemporizador() {
		return temporizador;
	}

	public void setTemporizador(String temporizador) {
		this.temporizador = temporizador;
	}

	@Column(name = "estado_carga_central")
	public String getEstadoCargaCentral() {
		return estadoCargaCentral;
	}

	public void setEstadoCargaCentral(String estadoCargaCentral) {
		this.estadoCargaCentral = estadoCargaCentral;
	}

	@Column(name = "marchar") // Marca de la fuente
	public String getMarchar() {
		return marchar;
	}

	public void setMarchar(String marchar) {
		this.marchar = marchar;
	}

	@Column(name = "modelo_fuente")
	public String getModeloFuente() {
		return modeloFuente;
	}

	public void setModeloFuente(String modeloFuente) {
		this.modeloFuente = modeloFuente;
	}

	@Column(name = "corriente")
	public String getCorriente() {
		return corriente;
	}

	public void setCorriente(String corriente) {
		this.corriente = corriente;
	}

	@Column(name = "tipo_bateria_fuente")
	public String getTipoBateriaFuente() {
		return tipoBateriaFuente;
	}

	public void setTipoBateriaFuente(String tipoBateriaFuente) {
		this.tipoBateriaFuente = tipoBateriaFuente;
	}

	@Column(name = "estado_carga_fuente")
	public String getEstadoCargaFuente() {
		return estadoCargaFuente;
	}

	public void setEstadoCargaFuente(String estadoCargaFuente) {
		this.estadoCargaFuente = estadoCargaFuente;
	}

	@Column(name = "ubi_detectores")
	public String getUbiDetectores() {
		return ubiDetectores;
	}

	public void setUbiDetectores(String ubiDetectores) {
		this.ubiDetectores = ubiDetectores;
	}

	@Column(name = "tipo_detectores")
	public String getTipoDetectores() {
		return tipoDetectores;
	}

	public void setTipoDetectores(String tipoDetectores) {
		this.tipoDetectores = tipoDetectores;
	}

	@Column(name = "marca_detectores")
	public String getMarcaDetectores() {
		return marcaDetectores;
	}

	public void setMarcaDetectores(String marcaDetectores) {
		this.marcaDetectores = marcaDetectores;
	}

	@Column(name = "ano_detectores")
	public String getAnoDetectores() {
		return anoDetectores;
	}

	public void setAnoDetectores(String anoDetectores) {
		this.anoDetectores = anoDetectores;
	}

	@Column(name = "zona_detectores")
	public String getZonaDetectores() {
		return zonaDetectores;
	}

	public void setZonaDetectores(String zonaDetectores) {
		this.zonaDetectores = zonaDetectores;
	}

	@Column(name = "estado_detectores")
	public String getEstadoDetectores() {
		return estadoDetectores;
	}

	public void setEstadoDetectores(String estadoDetectores) {
		this.estadoDetectores = estadoDetectores;
	}

	@Column(name = "funcion_detectores")
	public String getFuncionDetectores() {
		return funcionDetectores;
	}

	public void setFuncionDetectores(String funcionDetectores) {
		this.funcionDetectores = funcionDetectores;
	}

	@Column(name = "ubi_pulsadores")
	public String getUbiPulsadores() {
		return ubiPulsadores;
	}
	
	@Column(name = "cantidad_pulsadores")
	public String getCantidadPulsadores() {
		return cantidadPulsadores;
	}

	public void setCantidadPulsadores(String cantidadPulsadores) {
		this.cantidadPulsadores = cantidadPulsadores;
	}

	public void setUbiPulsadores(String ubiPulsadores) {
		this.ubiPulsadores = ubiPulsadores;
	}

	@Column(name = "tipo_pulsadores")
	public String getTipoPulsadores() {
		return tipoPulsadores;
	}

	public void setTipoPulsadores(String tipoPulsadores) {
		this.tipoPulsadores = tipoPulsadores;
	}

	@Column(name = "marca_pulsadores")
	public String getMarcaPulsadores() {
		return marcaPulsadores;
	}

	public void setMarcaPulsadores(String marcaPulsadores) {
		this.marcaPulsadores = marcaPulsadores;
	}

	@Column(name = "ano_pulsadores")
	public String getAnoPulsadores() {
		return anoPulsadores;
	}

	public void setAnoPulsadores(String anoPulsadores) {
		this.anoPulsadores = anoPulsadores;
	}

	@Column(name = "zona_pulsadores")
	public String getZonaPulsadores() {
		return zonaPulsadores;
	}

	public void setZonaPulsadores(String zonaPulsadores) {
		this.zonaPulsadores = zonaPulsadores;
	}

	@Column(name = "estado_pulsadores")
	public String getEstadoPulsadores() {
		return estadoPulsadores;
	}

	public void setEstadoPulsadores(String estadoPulsadores) {
		this.estadoPulsadores = estadoPulsadores;
	}

	@Column(name = "ubi_sirenas")
	public String getUbiSirenas() {
		return ubiSirenas;
	}

	public void setUbiSirenas(String ubiSirenas) {
		this.ubiSirenas = ubiSirenas;
	}
	
	@Column(name = "cantidad_sirenas")
	public String getCantidadSirenas() {
		return cantidadSirenas;
	}

	public void setCantidadSirenas(String cantidadSirenas) {
		this.cantidadSirenas = cantidadSirenas;
	}

	@Column(name = "tipo_sirenas")
	public String getTipoSirenas() {
		return tipoSirenas;
	}

	public void setTipoSirenas(String tipoSirenas) {
		this.tipoSirenas = tipoSirenas;
	}

	@Column(name = "marca_sirenas")
	public String getMarcaSirenas() {
		return marcaSirenas;
	}

	public void setMarcaSirenas(String marcaSirenas) {
		this.marcaSirenas = marcaSirenas;
	}

	@Column(name = "ano_sirenas")
	public String getAnoSirenas() {
		return anoSirenas;
	}

	public void setAnoSirenas(String anoSirenas) {
		this.anoSirenas = anoSirenas;
	}

	@Column(name = "zona_sirenas")
	public String getZonaSirenas() {
		return zonaSirenas;
	}

	public void setZonaSirenas(String zonaSirenas) {
		this.zonaSirenas = zonaSirenas;
	}

	@Column(name = "estado_sirenas")
	public String getEstadoSirenas() {
		return estadoSirenas;
	}

	public void setEstadoSirenas(String estadoSirenas) {
		this.estadoSirenas = estadoSirenas;
	}

	@Column(name = "cantidad_equipo_auxiliar")
	public String getCantidadEquipoAuxiliar() {
		return cantidadEquipoAuxiliar;
	}

	public void setCantidadEquipoAuxiliar(String cantidadEquipoAuxiliar) {
		this.cantidadEquipoAuxiliar = cantidadEquipoAuxiliar;
	}

	@Column(name = "ubicacion_equipo_auxiliar")
	public String getUbicacionEquipoAuxiliar() {
		return ubicacionEquipoAuxiliar;
	}

	public void setUbicacionEquipoAuxiliar(String ubicacionEquipoAuxiliar) {
		this.ubicacionEquipoAuxiliar = ubicacionEquipoAuxiliar;
	}

	@Column(name = "tipo_equipo_auxiliar")
	public String getTipoEquipoAuxiliar() {
		return tipoEquipoAuxiliar;
	}

	public void setTipoEquipoAuxiliar(String tipoEquipoAuxiliar) {
		this.tipoEquipoAuxiliar = tipoEquipoAuxiliar;
	}

	@Column(name = "marca_equipo_auxiliar")
	public String getMarcaEquipoAuxiliar() {
		return marcaEquipoAuxiliar;
	}

	public void setMarcaEquipoAuxiliar(String marcaEquipoAuxiliar) {
		this.marcaEquipoAuxiliar = marcaEquipoAuxiliar;
	}

	@Column(name = "ano_equipo_auxiliar")
	public String getAnoEquipoAuxiliar() {
		return anoEquipoAuxiliar;
	}

	public void setAnoEquipoAuxiliar(String anoEquipoAuxiliar) {
		this.anoEquipoAuxiliar = anoEquipoAuxiliar;
	}

	@Column(name = "zona_equipo_auxiliar")
	public String getZonaEquipoAuxiliar() {
		return zonaEquipoAuxiliar;
	}

	public void setZonaEquipoAuxiliar(String zonaEquipoAuxiliar) {
		this.zonaEquipoAuxiliar = zonaEquipoAuxiliar;
	}

	@Column(name = "estado_equipo_auxiliar")
	public String getEstadoEquipoAuxiliar() {
		return estadoEquipoAuxiliar;
	}

	public void setEstadoEquipoAuxiliar(String estadoEquipoAuxiliar) {
		this.estadoEquipoAuxiliar = estadoEquipoAuxiliar;
	}

	@Column(name = "cantidad_retenedor")
	public String getCantidadRetenedor() {
		return cantidadRetenedor;
	}

	public void setCantidadRetenedor(String cantidadRetenedor) {
		this.cantidadRetenedor = cantidadRetenedor;
	}

	@Column(name = "ubicacion_retenedor")
	public String getUbiRetenedor() {
		return ubiRetenedor;
	}

	public void setUbiRetenedor(String ubiRetenedor) {
		this.ubiRetenedor = ubiRetenedor;
	}

	@Column(name = "marca_retenedor")
	public String getMarcaRetenedor() {
		return marcaRetenedor;
	}

	public void setMarcaRetenedor(String marcaRetenedor) {
		this.marcaRetenedor = marcaRetenedor;
	}

	@Column(name = "ano_retenedor")
	public String getAnoRetenedor() {
		return anoRetenedor;
	}

	public void setAnoRetenedor(String anoRetenedor) {
		this.anoRetenedor = anoRetenedor;
	}

	@Column(name = "estado_retenedor")
	public String getEstadoRetenedor() {
		return estadoRetenedor;
	}

	public void setEstadoRetenedor(String estadoRetenedor) {
		this.estadoRetenedor = estadoRetenedor;
	}

	@Column(name = "cantidad_puertas")
	public String getCantidadPuertas() {
		return cantidadPuertas;
	}

	public void setCantidadPuertas(String cantidadPuertas) {
		this.cantidadPuertas = cantidadPuertas;
	}

	@Column(name = "ubicacion_puertas")
	public String getUbiPuertas() {
		return ubiPuertas;
	}

	public void setUbiPuertas(String ubiPuertas) {
		this.ubiPuertas = ubiPuertas;
	}

	@Column(name = "marca_puertas")
	public String getMarcaPuertas() {
		return marcaPuertas;
	}

	public void setMarcaPuertas(String marcaPuertas) {
		this.marcaPuertas = marcaPuertas;
	}

	@Column(name = "ano_puertas")
	public String getAnoPuertas() {
		return anoPuertas;
	}

	public void setAnoPuertas(String anoPuertas) {
		this.anoPuertas = anoPuertas;
	}

	@Column(name = "estado_puertas")
	public String getEstadoPuertas() {
		return estadoPuertas;
	}

	public void setEstadoPuertas(String estadoPuertas) {
		this.estadoPuertas = estadoPuertas;
	}
	
	@Column(name = "orden_central")
	public Integer getOrdenCentral() {
		return ordenCentral;
	}

	public void setOrdenCentral(Integer ordenCentral) {
		this.ordenCentral = ordenCentral;
	}

	@Column(name = "orden_fuente")
	public Integer getOrdenFuente() {
		return ordenFuente;
	}

	public void setOrdenFuente(Integer ordenFuente) {
		this.ordenFuente = ordenFuente;
	}

	@Column(name = "orden_detectores")
	public Integer getOrdenDetectores() {
		return ordenDetectores;
	}

	public void setOrdenDetectores(Integer ordenDetectores) {
		this.ordenDetectores = ordenDetectores;
	}

	@Column(name = "orden_pulsadores")
	public Integer getOrdenPulsadores() {
		return ordenPulsadores;
	}

	public void setOrdenPulsadores(Integer ordenPulsadores) {
		this.ordenPulsadores = ordenPulsadores;
	}

	@Column(name = "orden_sirenas")
	public Integer getOrdenSirenas() {
		return ordenSirenas;
	}

	public void setOrdenSirenas(Integer ordenSirenas) {
		this.ordenSirenas = ordenSirenas;
	}

	@Column(name = "orden_equipo_auxiliar")
	public Integer getOrdenEquipoAuxiliar() {
		return ordenEquipoAuxiliar;
	}

	public void setOrdenEquipoAuxiliar(Integer ordenEquipoAuxiliar) {
		this.ordenEquipoAuxiliar = ordenEquipoAuxiliar;
	}

	@Column(name = "orden_retenedor")
	public Integer getOrdenRetenedor() {
		return ordenRetenedor;
	}

	public void setOrdenRetenedor(Integer ordenRetenedor) {
		this.ordenRetenedor = ordenRetenedor;
	}

	@Column(name = "orden_puertas")
	public Integer getOrdenPuertas() {
		return ordenPuertas;
	}

	public void setOrdenPuertas(Integer ordenPuertas) {
		this.ordenPuertas = ordenPuertas;
	}

	@Column(name = "precio_central", precision = 17, scale = 17)
	public Double getPrecioCentral() {
		return precioCentral;
	}

	public void setPrecioCentral(Double precioCentral) {
		this.precioCentral = precioCentral;
	}

	@Column(name = "precio_fuente", precision = 17, scale = 17)
	public Double getPrecioFuente() {
		return precioFuente;
	}

	public void setPrecioFuente(Double precioFuente) {
		this.precioFuente = precioFuente;
	}

	@Column(name = "precio_detectores", precision = 17, scale = 17)
	public Double getPrecioDetectores() {
		return precioDetectores;
	}

	public void setPrecioDetectores(Double precioDetectores) {
		this.precioDetectores = precioDetectores;
	}

	@Column(name = "precio_pulsadores", precision = 17, scale = 17)
	public Double getPrecioPulsadores() {
		return precioPulsadores;
	}

	public void setPrecioPulsadores(Double precioPulsadores) {
		this.precioPulsadores = precioPulsadores;
	}

	@Column(name = "precio_sirenas", precision = 17, scale = 17)
	public Double getPrecioSirenas() {
		return precioSirenas;
	}

	public void setPrecioSirenas(Double precioSirenas) {
		this.precioSirenas = precioSirenas;
	}

	@Column(name = "precio_equipo_auxiliar", precision = 17, scale = 17)
	public Double getPrecioEquipoAuxiliar() {
		return precioEquipoAuxiliar;
	}

	public void setPrecioEquipoAuxiliar(Double precioEquipoAuxiliar) {
		this.precioEquipoAuxiliar = precioEquipoAuxiliar;
	}

	@Column(name = "precio_retenedor", precision = 17, scale = 17)
	public Double getPrecioRetenedor() {
		return precioRetenedor;
	}

	public void setPrecioRetenedor(Double precioRetenedor) {
		this.precioRetenedor = precioRetenedor;
	}

	@Column(name = "precio_puertas")
	public Double getPrecioPuertas() {
		return precioPuertas;
	}

	public void setPrecioPuertas(Double precioPuertas) {
		this.precioPuertas = precioPuertas;
	}

	@Column(name = "cantidad_luminaria")
	public String getCantidadLuminaria() {
		return cantidadLuminaria;
	}

	public void setCantidadLuminaria(String cantidadLuminaria) {
		this.cantidadLuminaria = cantidadLuminaria;
	}

	@Column(name = "ubicacion_luminaria")
	public String getUbicacionLuminaria() {
		return ubicacionLuminaria;
	}

	public void setUbicacionLuminaria(String ubicacionLuminaria) {
		this.ubicacionLuminaria = ubicacionLuminaria;
	}

	@Column(name = "ano_luminaria")
	public String getAnoLuminaria() {
		return anoLuminaria;
	}

	public void setAnoLuminaria(String anoLuminaria) {
		this.anoLuminaria = anoLuminaria;
	}

	@Column(name = "lumenes")
	public String getLumenes() {
		return lumenes;
	}

	public void setLumenes(String lumenes) {
		this.lumenes = lumenes;
	}

	@Column(name = "cantidad_detectores")
	public String getCantidadDetectores() {
		return cantidadDetectores;
	}

	public void setCantidadDetectores(String cantidadDetectores) {
		this.cantidadDetectores = cantidadDetectores;
	}
	
}
