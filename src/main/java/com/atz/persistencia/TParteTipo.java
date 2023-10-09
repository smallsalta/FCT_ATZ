package com.atz.persistencia;
// Generated 30 abr 2023 19:40:50 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TParteTipo generated by hbm2java
 */
@Entity
@Table(name = "t_parte_tipo", schema = "public")
public class TParteTipo implements java.io.Serializable {

	private int oid;
	private String descripcion;
	private Set<TParte> TPartes = new HashSet<TParte>(0);

	public TParteTipo() {
	}

	public TParteTipo(int oid) {
		this.oid = oid;
	}

	public TParteTipo(int oid, String descripcion, Set<TParte> TPartes) {
		this.oid = oid;
		this.descripcion = descripcion;
		this.TPartes = TPartes;
	}

	@Id

	@Column(name = "oid", unique = true, nullable = false)
	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TParteTipo")
	public Set<TParte> getTPartes() {
		return this.TPartes;
	}

	public void setTPartes(Set<TParte> TPartes) {
		this.TPartes = TPartes;
	}

}
