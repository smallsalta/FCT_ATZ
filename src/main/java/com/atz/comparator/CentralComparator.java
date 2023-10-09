package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class CentralComparator extends TParteLineaComparator{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenCentral().compareTo(o2.getOrdenCentral());
	}
}
