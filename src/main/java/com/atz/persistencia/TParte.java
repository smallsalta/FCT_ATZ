package com.atz.persistencia;
// Generated 14 may 2023 9:28:57 by Hibernate Tools 4.3.6.Final

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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

import com.atz.comparator.TParteLineaComparator;
import com.atz.comparator.TPreguntasParteComp;

/**
 * TParte generated by hbm2java
 */
@Entity
@Table(name = "t_parte", schema = "public")
public class TParte implements java.io.Serializable {

	private int oid;
	private TCliente TCliente;
	private TParteTipo TParteTipo;
	private TEstadoParte estado;
	private TEstado estado2;
	private TUsuario TUsuario;
	private Date fecha;
	private Date auditoria1;
	private Date auditoria2;
	private Integer numero;
	private List<TParteLinea> TParteLineas = new LinkedList<>();
	private String observaciones;
	private String dni;
	private Date auditoriaEmail;
	private String ccEmail;
	private Set<TPreguntasParte> TPreguntasParte = new LinkedHashSet<>(0);
	private String anterior;
	private Double cmto;

	public TParte() {
	}

	public TParte(int oid) {
		this.oid = oid;
	}

	public TParte(int oid, TCliente TCliente, TParteTipo TParteTipo, TUsuario TUsuario, Date fecha, Date auditoria1,
			Date auditoria2, Integer numero, List<TParteLinea> TParteLineas) {
		this.oid = oid;
		this.TCliente = TCliente;
		this.TParteTipo = TParteTipo;
		this.TUsuario = TUsuario;
		this.fecha = fecha;
		this.auditoria1 = auditoria1;
		this.auditoria2 = auditoria2;
		this.numero = numero;
		this.TParteLineas = TParteLineas;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_t_parte_tipo")
	public TParteTipo getTParteTipo() {
		return this.TParteTipo;
	}

	public void setTParteTipo(TParteTipo TParteTipo) {
		this.TParteTipo = TParteTipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado")
	public TEstadoParte getEstado() 
	{
		return estado;
	}
	
	public void setEstado(TEstadoParte estado) 
	{
		this.estado = estado;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado2")
	public TEstado getEstado2() 
	{
		return estado2;
	}
	
	public void setEstado2(TEstado estado2) 
	{
		this.estado2 = estado2;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_t_usuario")
	public TUsuario getTUsuario() {
		return this.TUsuario;
	}

	public void setTUsuario(TUsuario TUsuario) {
		this.TUsuario = TUsuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", length = 13)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auditoria1", length = 29)
	public Date getAuditoria1() {
		return this.auditoria1;
	}

	public void setAuditoria1(Date auditoria1) {
		this.auditoria1 = auditoria1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auditoria2", length = 29)
	public Date getAuditoria2() {
		return this.auditoria2;
	}

	public void setAuditoria2(Date auditoria2) {
		this.auditoria2 = auditoria2;
	}

	@Column(name = "numero")
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TParte", cascade = {CascadeType.ALL})
	//@SortComparator(TParteLineaComparator.class)
	public List<TParteLinea> getTParteLineas() {
		return this.TParteLineas;
	}

	public void setTParteLineas(List<TParteLinea> TParteLineas) {
		this.TParteLineas = TParteLineas;
	}

	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "dni")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TParte", cascade=CascadeType.ALL)
	public Set<TPreguntasParte> getTPreguntasParte() {
		return TPreguntasParte;
	}

	public void setTPreguntasParte(Set<TPreguntasParte> tPreguntasParte) {
		this.TPreguntasParte = new LinkedHashSet<>();
		this.TPreguntasParte = tPreguntasParte;
	}
	
	@Transient
	public List<TPreguntasParte> getTPreguntasParteOrd() {
		List<TPreguntasParte> p = Arrays.asList(this.TPreguntasParte.toArray(new TPreguntasParte[0]));
		Collections.sort(p, new TPreguntasParteComp());
		return p;
	}
	
	@Transient
	public List<TParteLinea> getTParteLineasOrd() {
		this.getTParteLineas().sort(new TParteLineaComparator());
		return this.TParteLineas;
	}

	@Column(name = "anterior")
	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}
	
	@Column(name = "cmto")
	public Double getCmto() {
		return cmto;
	}

	public void setCmto(Double cmto) {
		this.cmto = cmto;
	}
}
