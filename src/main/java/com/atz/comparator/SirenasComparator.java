package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class SirenasComparator extends TParteLineaComparator{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenSirenas().compareTo(o2.getOrdenSirenas());
	}
}
