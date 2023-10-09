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
 * TAgente generated by hbm2java
 */
@Entity
@Table(name = "t_agente", schema = "public")
public class TAgente implements java.io.Serializable {

	private int oid;
	private String descr;
	private Set<TLineaContrato> TLineaContratos = new HashSet<TLineaContrato>(0);

	public TAgente() {
	}

	public TAgente(int oid) {
		this.oid = oid;
	}

	public TAgente(int oid, String descr, Set<TLineaContrato> TLineaContratos) {
		this.oid = oid;
		this.descr = descr;
		this.TLineaContratos = TLineaContratos;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TAgente")
	public Set<TLineaContrato> getTLineaContratos() {
		return this.TLineaContratos;
	}

	public void setTLineaContratos(Set<TLineaContrato> TLineaContratos) {
		this.TLineaContratos = TLineaContratos;
	}

	@Override
	public boolean equals(Object obj) 
	{
		return this.oid == ((TAgente)obj).oid;
	}
}
