package com.atz.pdf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
public class ExtintorFbArray 
implements JRDataSource
{
	private List<ExtintorFb> extintores;
	private ExtintorFb actual;
	private int siguiente;
	
	public ExtintorFbArray()
	{
		this( new ArrayList<>() );
	}
	
	public ExtintorFbArray(List<ExtintorFb> ext) 
	{
		this.extintores = new ArrayList<>(ext);
		this.actual		= null;
		this.siguiente	= 0;
	}
	
	@Override
	public Object getFieldValue(JRField arg0) 
	throws JRException 
	{
		Object resp = null;
		
		switch( arg0.getName() )
		{
		
			case "cantidad":
				resp = this.actual.getCantidad(); 
				break;
			case "descripcion": 
				resp = this.actual.getDescripcion();
				break;
			case "agente": 
				resp = this.actual.getAgente();
				break;
			case "capacidad": 
				resp = this.actual.getCapacidad();
				break;
			case "fabricante": 
				resp = this.actual.getFabricante();
				break;
			case "fFabricacion": 
				resp = this.actual.getFFabricacion();
				break;
			case "fRetimbrado": 
				resp = this.actual.getFRetimbrado();
				break;
			case "prueba": 
				resp = this.actual.getPrueba();
				break;
			case "nplaca": 
				resp = this.actual.getNplaca();
				break;	
		}
		
		return resp;
	}

	@Override
	public boolean next() 
	throws JRException 
	{
		boolean next = true;
		
		try
		{
			this.actual = this.extintores.get( this.siguiente );
			this.siguiente++;
		}
		catch(IndexOutOfBoundsException e)
		{
			next = false;
		}
		
		return next;
	}
}
