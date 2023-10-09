package com.atz.persistencia;
// Generated 24-dic-2018 13:19:13 by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TEmpresa generated by hbm2java
 */
@Entity
@Table(name = "t_empresa", schema = "public")
public class TEmpresa implements java.io.Serializable {

	private int oid;
	private String descr;
	private String jasper;
	private int orden;
	private Set<TFactura> TFacturas = new HashSet<TFactura>(0);

	public TEmpresa() {
	}

	public TEmpresa(int oid) {
		this.oid = oid;
	}

	public TEmpresa(int oid, String descr, String jasper, Set<TFactura> TFacturas) {
		this.oid = oid;
		this.descr = descr;
		this.jasper = jasper;
		this.TFacturas = TFacturas;
	}

	@Id

	@Column(name = "oid", unique = true, nullable = false)
	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Column(name = "descr")
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "jasper")
	public String getJasper() {
		return this.jasper;
	}

	public void setJasper(String jasper) {
		this.jasper = jasper;
	}
	
	@Column(name = "orden")
	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TEmpresa")
	public Set<TFactura> getTFacturas() {
		return this.TFacturas;
	}

	public void setTFacturas(Set<TFactura> TFacturas) {
		this.TFacturas = TFacturas;
	}

}
