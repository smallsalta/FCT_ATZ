package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TLineaContrato;

public class TLineaContratoComp 
implements Comparator<TLineaContrato>
{
	@Override
	public int compare(TLineaContrato o1, TLineaContrato o2) 
	{
		Integer i1 = Integer.valueOf( o1.getOid() );
		Integer i2 = Integer.valueOf( o2.getOid() );
		
		return i1.compareTo(i2);
	}
}
