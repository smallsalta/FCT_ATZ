package com.atz.persistencia;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_estado_parte", schema = "public")
public class TEstadoParte implements java.io.Serializable {

	private int oid;
	private String descripcion;
	private Set<TParte> TPartes = new HashSet<>(0);
	
	public TEstadoParte() {
		
	}
	
	public TEstadoParte(int oid) {
		this.oid = oid;
	}
	
	public TEstadoParte(int oid, String descripcion, Set<TParte> TPartes) {
		this.oid = oid;
		this.descripcion = descripcion;
		this.TPartes = TPartes;
	}

	@Id

	@Column(name = "oid", unique = true, nullable = false)
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="estado")
	public Set<TParte> getTPartes() {
		return TPartes;
	}

	public void setTPartes(Set<TParte> TPartes) {
		this.TPartes = TPartes;
	}
	
	
}
