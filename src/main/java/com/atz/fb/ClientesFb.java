package com.atz.fb;

import lombok.Data;

@Data
public class ClientesFb 
{
	private Integer oid;
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String localidad;
	private String provincia;
	private Integer cp;
	private Integer telefono1;
	private Integer telefono2;
	private String observaciones;
	private String email;
	private Integer oidUsuario;
}
