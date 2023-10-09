package com.atz.persistencia;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TSelectCentralita {
	
	private int oid;
	private String descr;
	
	@Id
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
	
}
