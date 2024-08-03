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
@Table(name = "t_tipo_bie_bomba", schema = "public")
public class TTipoBieBomba {

	private int oid;
	private String tipo;
	private Set<TParteLinea> TParteLineas = new HashSet<>(0);
	
	public TTipoBieBomba() {
		
	}
	
	public TTipoBieBomba(int oid) {
		this.oid = oid;
	}
	
	public TTipoBieBomba(int oid, String tipo, Set<TParteLinea> TParteLineas) {
		this.oid = oid;
		this.tipo = tipo;
		this.TParteLineas = TParteLineas;
	}
	
	@Id

	@Column(name = "oid", unique = true, nullable = false)
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@Column(name = "tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="tipoBomba")
	public Set<TParteLinea> getTParteLineas() {
		return TParteLineas;
	}

	public void setTParteLineas(Set<TParteLinea> tParteLineas) {
		TParteLineas = tParteLineas;
	}
}
