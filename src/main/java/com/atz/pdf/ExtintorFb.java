package com.atz.pdf;

import java.util.Date;

import lombok.Data;

@Data
public class ExtintorFb 
{
	private double cantidad;
	private String descripcion;
	private String agente;
	private float capacidad;
	private String fabricante;
	private Date fFabricacion;
	private Date fRetimbrado;
	private String prueba;
	private String nplaca;
	
	public ExtintorFb()
	{
		this.cantidad 		= 1F;
		this.descripcion	= "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
		this.agente 		= "ABC";
		this.capacidad 		= 6;
		this.fabricante 	= "Ninsu";
		this.fFabricacion 	= new Date();
		this.fRetimbrado 	= new Date();
		this.prueba 		= "RV";
		this.nplaca			= "xxx";
	}
}
