package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TEmpresa;

public class TEmpresaComp 
implements Comparator<TEmpresa>
{
	@Override
	public int compare(TEmpresa o1, TEmpresa o2) 
	{
		Integer i1 = Integer.valueOf( o1.getOrden() );
		Integer i2 = Integer.valueOf( o2.getOrden() );
		
		return i1.compareTo(i2);
	}
}
