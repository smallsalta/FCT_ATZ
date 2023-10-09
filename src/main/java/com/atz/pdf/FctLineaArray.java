package com.atz.pdf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
public class FctLineaArray 
implements JRDataSource
{
	private List<FctLineaFb> lineas;
	private FctLineaFb actual;
	private int siguiente;
	
	public FctLineaArray()
	{
		this( new ArrayList<>() );
	}
	
	public FctLineaArray(List<FctLineaFb> ext) 
	{
		this.lineas 	= new ArrayList<>(ext);
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
			case "precio": 
				resp = this.actual.getPrecio();
				break;
			case "descuento": 
				resp = this.actual.getDescuento();
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
			this.actual = this.lineas.get( this.siguiente );
			this.siguiente++;
		}
		catch(IndexOutOfBoundsException e)
		{
			next = false;
		}
		
		return next;
	}
}
