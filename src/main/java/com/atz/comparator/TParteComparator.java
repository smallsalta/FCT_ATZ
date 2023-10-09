package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TParte;

public class TParteComparator 
implements Comparator<TParte>
{
	@Override
	public int compare(TParte o1, TParte o2) 
	{
		return o1.getNumero().compareTo( o2.getNumero() );
	}

}
