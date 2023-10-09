package com.atz.enums;

public enum ComboSiNo {
	SI(1, "Si"), NO(2, "No");
	
	private int oid;
	private String valor;
	
	private ComboSiNo(int oid, String valor) {
		this.oid = oid;
		this.valor = valor;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
