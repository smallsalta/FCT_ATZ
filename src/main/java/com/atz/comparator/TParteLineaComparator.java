package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TParteLinea;

public class TParteLineaComparator implements Comparator<TParteLinea>{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrden().compareTo(o2.getOrden());
	}

}
