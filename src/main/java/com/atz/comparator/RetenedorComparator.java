package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class RetenedorComparator extends TParteLineaComparator{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenRetenedor().compareTo(o2.getOrdenRetenedor());
	}
}
