package com.atz.pdf;

import lombok.Data;

@Data
public class PreguntaFb 
{
	private String pregunta;
	private String respuesta;
	
	public PreguntaFb()
	{	
		this.pregunta	= "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
		this.respuesta 	= "S";
	}
}
