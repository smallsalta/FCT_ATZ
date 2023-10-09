package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TPreguntas;

public class TPreguntasComp 
implements Comparator<TPreguntas>
{
	@Override
	public int compare(TPreguntas o1, TPreguntas o2) 
	{
		Integer i1 = Integer.valueOf( o1.getOid() );
		Integer i2 = Integer.valueOf( o2.getOid() );
		
		return i1.compareTo(i2);
	}
}
