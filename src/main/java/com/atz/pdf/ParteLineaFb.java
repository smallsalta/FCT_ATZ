package com.atz.pdf;

import java.util.Date;

import lombok.Data;

@Data
public class ParteLineaFb {

	private String orden;
	private String tipo;
	private String ubicacion;
	
	
	private String numPlaca;
	private String kg;
	private String fabricante;
	private Date fechaFabricacion;
	private Date ultimoRetimbrado;
	private String rv;
	private String rc;
	private String rt;
	private String nuevo;
	private Date cartel;
	private String altura;
	private String precio;
	
	private String longMang;
	private Date fechaRetimA;
	private Date fechaRetimB;
	private String presionEstatica;
	private String presionDinamica;
	private String manguera;
	private String lanza;
	private String valvula;
	private String manometro;
	private String cristal;
	private String sennales;
	private String estadoGeneral;
	private String numSerie;
	
	private String cantidad;
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
    private String funcionDetectores;
    private String anoDetectores;
    private String zonaDetectores;
    private String estadoDetectores;
    
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
    
    private String precioCentral;
    private String precioFuente;
    private String precioDetectores;
    private String precioPulsadores;
    private String precioSirenas;
    private String precioEquipoAuxiliar;
    private String precioRetenedor;
    private String precioPuertas;
    
    private String cantidadLuminaria;
    private String ubicacionLuminaria;
    private String anoLuminaria;
    private String lumenes;
    
    private String cantidadDetectores;
	
	public void setNumeroSerie(String numSerie) {
		this.numSerie = numSerie;
	}
}
