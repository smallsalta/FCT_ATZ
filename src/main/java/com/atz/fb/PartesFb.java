package com.atz.fb;

import java.util.Date;

import lombok.Data;

@Data
public class PartesFb {

	private Integer oid;
	private Integer oidcliente;
	private Integer oidpartetipo;
	private Integer oidusuario;
	private Integer oidestadoparte;
	private Integer oidestado;
	private Date fecha;
	private Date auditoria1;
	private Date auditoria2;
	private String observaciones;
	private String dni;
	private String ccemail;
	private Integer numero;
	private String anterior;
	private Double cmto;
	private String dirtra;
	
	private Integer[] orden;
	private Integer[] tipo;
	
	// Extintores
	private String[] ubicacion;
	private String[] numPlaca;
	private Integer[] kg;
	private String[] fabricante;
	private Date[] fechaFabricacion;
	private Date[] ultimoRetimbrado;
	private Integer[] prueba;
	private Date[] cartel;
	private String[] altura;
	private Double[] precio;
	
	// BIE
	private String[] longMang;
	private Date[] fechaRetimA;
	private Date[] fechaRetimB;
	private Double[] presionEstatica;
	private Double[] presionDinamica;
	private String[] manguera;
	private String[] lanza;
	private String[] valvula;
	private String[] manometro;
	private String[] cristal;
	private String[] sennales;
	private String[] estadoGeneral;
	private String[] numSerie;
	
	// Observaciones
	private Integer[] cantidad;
	private String[] descripcion;
	
	// Centralita
	private String[] marca;
    private String[] pilotos;
    private String[] tipoBateriaCentral;
    private String[] tipoCentral;
    private String[] modelo;
    private String[] reles;
    private String[] unidades;
    private String[] nZona;
    private String[] zumbador;
    private String[] zonas;
    private String[] mandosControl;
    private String[] lineasCableado;
    private String[] zonasReserva;
    private String[] redIndep;
    private String[] temporizador;
    private String[] estadoCargaCentral;
    private String[] marchar;
    private String[] modeloFuente;
    private String[] corriente;
    private String[] tipoBateriaFuente;
    private String[] estadoCargaFuente;
    private String[] ubiDetectores;
    private String[] tipoDetectores;
    private String[] marcaDetectores;
    private String[] anoDetectores;
    private String[] zonaDetectores;
    private String[] estadoDetectores;
    private String[] funcionDetectores;
    private String[] ubiPulsadores;
    private String[] cantidadPulsadores;
    private String[] tipoPulsadores;
    private String[] marcaPulsadores;
    private String[] anoPulsadores;
    private String[] zonaPulsadores;
    private String[] estadoPulsadores;
    private String[] ubiSirenas;
    private String[] cantidadSirenas;
    private String[] tipoSirenas;
    private String[] marcaSirenas;
    private String[] anoSirenas;
    private String[] zonaSirenas;
    private String[] estadoSirenas;
    
    private String[] cantidadEquipoAuxiliar;
    private String[] ubicacionEquipoAuxiliar;
    private String[] tipoEquipoAuxiliar;
    private String[] marcaEquipoAuxiliar;
    private String[] anoEquipoAuxiliar;
    private String[] zonaEquipoAuxiliar;
    private String[] estadoEquipoAuxiliar;
    
    private String[] cantidadRetenedor;
    private String[] ubiRetenedor;
    private String[] marcaRetenedor;
    private String[] anoRetenedor;
    private String[] estadoRetenedor;
    
    private String[] cantidadPuertas;
    private String[] ubiPuertas;
    private String[] marcaPuertas;
    private String[] anoPuertas;
    private String[] estadoPuertas;
    
    private Integer[] ordenCentral;
    private Integer[] ordenFuente;
    private Integer[] ordenDetectores;
    private Integer[] ordenPulsadores;
    private Integer[] ordenSirenas;
    private Integer[] ordenEquipoAuxiliar;
    private Integer[] ordenRetenedor;
    private Integer[] ordenPuertas;
    
    private Double[] precioCentral;
    private Double[] precioFuente;
    private Double[] precioDetectores;
    private Double[] precioPulsadores;
    private Double[] precioSirenas;
    private Double[] precioEquipoAuxiliar;
    private Double[] precioRetenedor;
    private Double[] precioPuertas;
	
    private String[] cantidadLuminaria;
    private String[] ubicacionLuminaria;
    private String[] anoLuminaria;
    private String[] lumenes;
    
    private String[] cantidadDetectores;
    
	private String[] preguntas;
	
	public void init() {
		this.setOrden(new Integer[1]);
		this.setTipo(new Integer[1]);
		
		// Extintores
		this.setUbicacion(new String[1]);
		this.setNumPlaca(new String[1]);
		this.setKg(new Integer[1]);
		this.setFabricante(new String[1]);
		this.setFechaFabricacion(new Date[1]);
		this.setUltimoRetimbrado(new Date[1]);
		this.setPrueba(new Integer[1]);
		this.setCartel(new Date[1]);
		this.setAltura(new String[1]);
		this.setPrecio(new Double[1]);
		
		this.setOrdenCentral(new Integer[1]);
		this.setMarca(new String[1]);
		this.setPilotos(new String[1]);
		this.setTipoBateriaCentral(new String[1]);
		this.setTipoCentral(new String[1]);
		this.setModelo(new String[1]);
		this.setReles(new String[1]);
		this.setUnidades(new String[1]);
		this.setNZona(new String[1]);
		this.setZumbador(new String[1]);
		this.setZonas(new String[1]);
		this.setMandosControl(new String[1]);
		this.setLineasCableado(new String[1]);
		this.setZonasReserva(new String[1]);
		this.setRedIndep(new String[1]);
		this.setTemporizador(new String[1]);
		this.setEstadoCargaCentral(new String[1]);

		this.setOrdenFuente(new Integer[1]);
		this.setMarchar(new String[1]);
		this.setModeloFuente(new String[1]);
		this.setCorriente(new String[1]);
		this.setTipoBateriaFuente(new String[1]);
		this.setEstadoCargaFuente(new String[1]);

		this.setOrdenDetectores(new Integer[1]);
		this.setUbiDetectores(new String[1]);
		this.setTipoDetectores(new String[1]);
		this.setMarcaDetectores(new String[1]);
		this.setAnoDetectores(new String[1]);
		this.setZonaDetectores(new String[1]);
		this.setEstadoDetectores(new String[1]);

		this.setOrdenPulsadores(new Integer[1]);
		this.setUbiPulsadores(new String[1]);
		this.setCantidadPulsadores(new String[1]);
		this.setTipoPulsadores(new String[1]);
		this.setMarcaPulsadores(new String[1]);
		this.setAnoPulsadores(new String[1]);
		this.setZonaPulsadores(new String[1]);
		this.setEstadoPulsadores(new String[1]);

		this.setOrdenSirenas(new Integer[1]);
		this.setUbiSirenas(new String[1]);
		this.setCantidadSirenas(new String[1]);
		this.setTipoSirenas(new String[1]);
		this.setMarcaSirenas(new String[1]);
		this.setAnoSirenas(new String[1]);
		this.setZonaSirenas(new String[1]);
		this.setEstadoSirenas(new String[1]);
		
		this.setOrdenEquipoAuxiliar(new Integer[1]);
		this.setCantidadEquipoAuxiliar(new String[1]);
	    this.setUbicacionEquipoAuxiliar(new String[1]);
	    this.setTipoEquipoAuxiliar(new String[1]);
	    this.setMarcaEquipoAuxiliar(new String[1]);
	    this.setAnoEquipoAuxiliar(new String[1]);
	    this.setZonaEquipoAuxiliar(new String[1]);
	    this.setEstadoEquipoAuxiliar(new String[1]);
	    
	    this.setOrdenRetenedor(new Integer[1]);
	    this.setCantidadRetenedor(new String[1]);
	    this.setUbiRetenedor(new String[1]);
	    this.setMarcaRetenedor(new String[1]);
	    this.setAnoRetenedor(new String[1]);
	    this.setEstadoRetenedor(new String[1]);
	    
	    this.setOrdenPuertas(new Integer[1]);
	    this.setCantidadPuertas(new String[1]);
	    this.setUbiPuertas(new String[1]);
	    this.setMarcaPuertas(new String[1]);
	    this.setAnoPuertas(new String[1]);
	    this.setEstadoPuertas(new String[1]);
		
		this.setPrecioCentral(new Double[1]);
	    this.setPrecioFuente(new Double[1]);
	    this.setPrecioDetectores(new Double[1]);
	    this.setPrecioPulsadores(new Double[1]);
	    this.setPrecioSirenas(new Double[1]);
	    this.setPrecioEquipoAuxiliar(new Double[1]);
	    this.setPrecioRetenedor(new Double[1]);
	    this.setPrecioPuertas(new Double[1]);
	    
	    this.setCantidadLuminaria(new String[1]);
	    this.setUbicacionLuminaria(new String[1]);
	    this.setAnoLuminaria(new String[1]);
	    this.setLumenes(new String[1]);
	    
	    this.setCantidadDetectores(new String[1]);
	}
	
}
