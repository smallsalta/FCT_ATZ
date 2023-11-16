package com.atz.fb;

import java.util.Date;

import lombok.Data;

@Data
public class PicadasFb 
{
	private Integer oid;
	
	private Date fecha;
	private String hini;
	private String hfin;
	private String info;
	
	private Date fini;
	private Date ffin;
	private int oidusuario;
}
