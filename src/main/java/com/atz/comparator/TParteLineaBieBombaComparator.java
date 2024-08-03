package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TParteLinea;

public class TParteLineaBieBombaComparator implements Comparator<TParteLinea>{

	@Override
	public int compare(TParteLinea o1, TParteLinea o2) {
		// TODO Auto-generated method stub
		return o1.getOrdenBomba().compareTo(o2.getOrdenBomba());
	}

}
