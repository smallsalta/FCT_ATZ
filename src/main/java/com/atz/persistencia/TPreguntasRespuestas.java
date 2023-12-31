package com.atz.persistencia;
// Generated 08-ago-2018 21:11:53 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TPreguntasRespuestas generated by hbm2java
 */
@Entity
@Table(name = "t_preguntas_respuestas", schema = "public")
public class TPreguntasRespuestas implements java.io.Serializable {

	private int oid;
	private String descr;
	private Set<TPreguntasContrato> TPreguntasContratos = new HashSet<TPreguntasContrato>(0);

	public TPreguntasRespuestas() {
	}

	public TPreguntasRespuestas(int oid) {
		this.oid = oid;
	}

	public TPreguntasRespuestas(int oid, String descr, Set<TPreguntasContrato> TPreguntasContratos) {
		this.oid = oid;
		this.descr = descr;
		this.TPreguntasContratos = TPreguntasContratos;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TPreguntasRespuestas")
	public Set<TPreguntasContrato> getTPreguntasContratos() {
		return this.TPreguntasContratos;
	}

	public void setTPreguntasContratos(Set<TPreguntasContrato> TPreguntasContratos) {
		this.TPreguntasContratos = TPreguntasContratos;
	}

}
