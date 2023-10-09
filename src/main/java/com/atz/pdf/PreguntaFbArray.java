package com.atz.pdf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
public class PreguntaFbArray 
implements JRDataSource
{
	private List<PreguntaFb> preguntas;
	private PreguntaFb actual;
	private int siguiente;
	
	public PreguntaFbArray()
	{
		this( new ArrayList<>() );
	}
	
	public PreguntaFbArray(List<PreguntaFb> ext) 
	{
		this.preguntas	= new ArrayList<>(ext);
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
			case "pregunta":
				resp = this.actual.getPregunta(); 
				break;
			case "respuesta": 
				resp = this.actual.getRespuesta();
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
			this.actual = this.preguntas.get( this.siguiente );
			this.siguiente++;
		}
		catch(IndexOutOfBoundsException e)
		{
			next = false;
		}
		
		return next;
	}
}
