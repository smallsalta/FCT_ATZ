package com.atz.persistencia;
// Generated 08-ago-2018 21:11:53 by Hibernate Tools 5.2.11.Final

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.atz.comparator.TLineaContratoComp;
import com.atz.comparator.TPreguntasContratoComp;

/**
 * TContrato generated by hbm2java
 */
@Entity
@Table(name = "t_contrato", schema = "public")
public class TContrato implements java.io.Serializable {

	private int oid;
	private TCliente TCliente;
	private Date fecha;
	private Date auditoria1;
	private Date auditoria2;
	private Integer numero;
	private Double trimestral;
	private String anexo;
	private String direccion;
	private Date auditoriaEmail;
	private String ccEmail;
	private Set<TLineaContrato> TLineaContratos = new LinkedHashSet<TLineaContrato>(0);
	private Set<TPreguntasContrato> TPreguntasContratos = new LinkedHashSet<TPreguntasContrato>(0);

	public TContrato() 
	{
		this.trimestral = 0D;
	}

	public TContrato(int oid) 
	{
		this();
		this.oid = oid;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_t_cliente")
	public TCliente getTCliente() {
		return this.TCliente;
	}

	public void setTCliente(TCliente TCliente) {
		this.TCliente = TCliente;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", length = 13)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "numero")
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "anexo")
	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	@Column(name = "direccion")
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TContrato", cascade=CascadeType.ALL)
	public Set<TLineaContrato> getTLineaContratos() 
	{
		return this.TLineaContratos;
	}
	
	public void setTLineaContratos(Set<TLineaContrato> TLineaContratos) {
		this.TLineaContratos = new LinkedHashSet<>();
		this.TLineaContratos.addAll(TLineaContratos);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TContrato", cascade=CascadeType.ALL)
	public Set<TPreguntasContrato> getTPreguntasContratos() {
		return this.TPreguntasContratos;
	}

	public void setTPreguntasContratos(Set<TPreguntasContrato> TPreguntasContratos) {
		this.TPreguntasContratos = new LinkedHashSet<>();
		this.TPreguntasContratos.addAll(TPreguntasContratos);
	}

	@Transient
	public List<TLineaContrato> getTLineaContratosOrd() 
	{
		List<TLineaContrato> lc = Arrays.asList( this.TLineaContratos.toArray( new TLineaContrato[0] ) );
		Collections.sort( lc, new TLineaContratoComp() );
		return lc;
	}
	
	@Transient
	public List<TPreguntasContrato> getTPreguntasContratosOrd() 
	{
		List<TPreguntasContrato> lc = Arrays.asList( this.TPreguntasContratos.toArray( new TPreguntasContrato[0] ) );
		Collections.sort( lc, new TPreguntasContratoComp() );
		return lc;
	}

	@Column(name = "trimestral")
	public Double getTrimestral() {
		return trimestral;
	}

	public void setTrimestral(Double trimestral) {
		this.trimestral = trimestral;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auditoria_1", length = 29)
	public Date getAuditoria1() {
		return this.auditoria1;
	}

	public void setAuditoria1(Date auditoria1) {
		this.auditoria1 = auditoria1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auditoria_2", length = 29)
	public Date getAuditoria2() {
		return this.auditoria2;
	}

	public void setAuditoria2(Date auditoria2) {
		this.auditoria2 = auditoria2;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auditoria_email", length = 29)
	public Date getAuditoriaEmail() {
		return auditoriaEmail;
	}

	public void setAuditoriaEmail(Date auditoriaEmail) {
		this.auditoriaEmail = auditoriaEmail;
	}

	@Column(name = "cc_email")
	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
}