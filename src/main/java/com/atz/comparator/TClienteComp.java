package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TCliente;

public class TClienteComp 
implements Comparator<TCliente>{

	@Override
	public int compare(TCliente o1, TCliente o2) {
		String c1 = (o1.getNombre() + " " + o1.getApellidos()).toUpperCase();
		String c2 = (o2.getNombre() + " " + o2.getApellidos()).toUpperCase();
		
		return c1.compareTo(c2);
	}
	
}
