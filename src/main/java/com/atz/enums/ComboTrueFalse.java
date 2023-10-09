package com.atz.enums;

public enum ComboTrueFalse {

SI(true, "Si"), NO(false, "No");
	
	private Boolean oid;
	private String valor;
	
	private ComboTrueFalse(Boolean oid, String valor) {
		this.oid = oid;
		this.valor = valor;
	}

	public Boolean getOid() {
		return oid;
	}

	public void setOid(Boolean oid) {
		this.oid = oid;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
