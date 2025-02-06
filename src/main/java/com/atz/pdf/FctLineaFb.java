package com.atz.pdf;

import lombok.Data;

@Data
public class FctLineaFb 
{
	private double cantidad;
	private String descripcion;
	private double precio;
	private double descuento;
	
	public FctLineaFb()
	{
		this( 1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit", 0D, 0D);
	}
	
	public FctLineaFb(double cantidad, String descripcion, double precio, double descuento)
	{
		this.cantidad 		= cantidad;
		this.descripcion	= descripcion;
		this.precio			= precio;
		this.descuento		= descuento;
	}
}
