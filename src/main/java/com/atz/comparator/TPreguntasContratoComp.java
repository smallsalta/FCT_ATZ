package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TPreguntasContrato;

public class TPreguntasContratoComp 
implements Comparator<TPreguntasContrato>
{
	@Override
	public int compare(TPreguntasContrato o1, TPreguntasContrato o2) 
	{
		Integer i1 = Integer.valueOf( o1.getOid() );
		Integer i2 = Integer.valueOf( o2.getOid() );
		
		return i1.compareTo(i2);
	}
}
