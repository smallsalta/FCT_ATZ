package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class FuenteComparator extends TParteLineaComparator{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenFuente().compareTo(o2.getOrdenFuente());
	}
}
