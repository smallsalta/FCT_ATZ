package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TPreguntasParte;

public class TPreguntasParteComp implements Comparator<TPreguntasParte> {

	@Override
	public int compare(TPreguntasParte o1, TPreguntasParte o2) {
		Integer i1 = Integer.valueOf( o1.getOid() );
		Integer i2 = Integer.valueOf( o2.getOid() );
		
		return i1.compareTo(i2);
	}

}
