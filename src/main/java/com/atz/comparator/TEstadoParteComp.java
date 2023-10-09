package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TEstadoParte;

public class TEstadoParteComp 
implements Comparator<TEstadoParte>
{
	@Override
	public int compare(TEstadoParte o1, TEstadoParte o2) 
	{
		return o1.getDescripcion().compareTo( o2.getDescripcion() );
	}

}
