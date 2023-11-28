package com.atz.enums;

public enum ComboLongBIE {
	TIPO1("25/20", "25/20"), 
	TIPO2("45/20", "45/20"), 
	TIPO3("J+P", "J+P"),
	TIPO4("J+D", "J+D"),
	TIPO5("J+E+P", "J+E+P"),
	TIPO6("12K l", "12.000 l"),
	TIPO7("24K l", "24.000 l"),
	TIPO8("30K l", "30.000 l"),
	TIPO9("40K l", "40.000 l");
	
	private String oid;
	private String valor;
	
	private ComboLongBIE(String oid, String valor) {
		this.oid = oid;
		this.valor = valor;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
