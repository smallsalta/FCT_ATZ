package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TFacturaLinea;

public class TFacturaLineaComp 
implements Comparator<TFacturaLinea>
{
	@Override
	public int compare(TFacturaLinea o1, TFacturaLinea o2) 
	{
		Integer i1 = Integer.valueOf( o1.getOid() );
		Integer i2 = Integer.valueOf( o2.getOid() );
		
		return i1.compareTo(i2);
	}
}
