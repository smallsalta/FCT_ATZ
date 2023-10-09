package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class PuertasComparator extends TParteLineaComparator{
	
	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenPuertas().compareTo(o2.getOrdenPuertas());
	}

}
