package com.atz.comparator;

import com.atz.persistencia.TParteLinea;

public class DetectoresComparator extends TParteLineaComparator{
	
	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		return o1.getOrdenDetectores().compareTo(o2.getOrdenDetectores());
	}
}
