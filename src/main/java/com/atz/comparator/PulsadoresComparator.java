package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class PulsadoresComparator extends TParteLineaComparator {
	
	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenPulsadores().compareTo(o2.getOrdenPulsadores());
	}
}
