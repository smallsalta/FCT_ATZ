package com.atz.fb;

import java.util.Date;

import lombok.Data;

@Data
public class ParteBuscarFb 
{
	private Date fini;
	private Date ffin;
	private Integer oidcliente;
	private Integer oidusuario;
	private Integer numero;
	private Integer oidpartetipo;
	private Integer oidestadoparte;
	private Integer nparte;
	private Integer nfactura;
	private Integer ncontrato;
	private Integer oidmatrimonio;
	private String tipoLupa;
}
