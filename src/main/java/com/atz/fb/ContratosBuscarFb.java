package com.atz.fb;

import java.util.Date;

import lombok.Data;

@Data
public class ContratosBuscarFb 
{
	private Integer oid;
	private Date fini;
	private Date ffin;
	private boolean checkcliente;
	private Integer oidcliente;
	private Integer oidempresa;
	private String[] fctoid;
	private String[] preguntas;
	private Integer numero;
}