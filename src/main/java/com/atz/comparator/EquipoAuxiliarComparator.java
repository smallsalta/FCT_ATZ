package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class EquipoAuxiliarComparator extends TParteLineaComparator{

	
	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenEquipoAuxiliar().compareTo(o2.getOrdenEquipoAuxiliar());
	}
}
