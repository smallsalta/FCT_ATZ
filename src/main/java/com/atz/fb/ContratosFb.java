package com.atz.fb;

import java.util.Date;

import com.atz.validation.Formato1;
import com.atz.validation.ValidateContratoParte;

import lombok.Data;

@Data
@ValidateContratoParte(groups = Formato1.class)
public class ContratosFb 
{
	private Integer oid;
	private Date fecha;
	private Integer numero;
	private Integer oidcliente;
	private Integer oidcliente2;
	private Integer oidempresa;
	private Integer oidestado;
	private Integer oidusuario;
	private String direccion;
	private String anexo;
	private Double iva;
	private Double trimestral;
	private Date auditoria1;
	private Date auditoria2;
	private String ccemail;
	private Double[] capacidadExt;
	private Double[] precioExt;
	private Double[] descuentoExt;
	private Date[] fechaFabExt;
	private Date[] fechaRetExt;
	private String[] numeroPlacaExt;
	private String[] descrExt;
	private String[] fabricanteExt;
	private Integer[] agentesExt;
	private Integer[] pruebasExt;
	private Integer[] cantidadExt;
	private String[] preguntas;
	private Double ajuste;
	private Integer oidperiodicidad;
	private Integer oidmodalidad;
	private Double precio;
	
	/**
	 * Inicializa los campos para el pase de parte a contrato
	 */
	public void init() {
		this.cantidadExt = new Integer[1];
		this.capacidadExt = new Double[1];
		this.precioExt = new Double[1];
		this.descuentoExt = new Double[1];
		this.fechaFabExt = new Date[1];
		this.fechaRetExt = new Date[1];
		this.numeroPlacaExt = new String[1];
		this.descrExt = new String[1];
		this.fabricanteExt = new String[1];
		this.agentesExt = new Integer[1];
		this.pruebasExt = new Integer[1];
		this.preguntas = new String[1];
	}
}
