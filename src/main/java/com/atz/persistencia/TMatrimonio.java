package com.atz.persistencia;
// Generated 25 sept 2023 0:18:13 by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMatrimonio generated by hbm2java
 */
@Entity
@Table(name = "t_matrimonio")
public class TMatrimonio implements java.io.Serializable {

	private int oid;
	private int parte;
	private Integer factura;
	private Integer contrato;
	private String numero2;

	public TMatrimonio() {
	}

	public TMatrimonio(int oid, int parte, Integer factura, Integer contrato) {
		this.oid = oid;
		this.parte = parte;
		this.factura = factura;
		this.contrato = contrato;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "oid", unique = true, nullable = false)
	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Column(name = "parte", nullable = false)
	public int getParte() {
		return this.parte;
	}

	public void setParte(int parte) {
		this.parte = parte;
	}

	@Column(name = "factura")
	public Integer getFactura() {
		return this.factura;
	}

	public void setFactura(Integer factura) {
		this.factura = factura;
	}

	@Column(name = "contrato")
	public Integer getContrato() {
		return this.contrato;
	}

	public void setContrato(Integer contrato) {
		this.contrato = contrato;
	}
	
	@Column(name = "numero2")
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "numero2")
	public String getNumero2() {
		return this.numero2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

}
