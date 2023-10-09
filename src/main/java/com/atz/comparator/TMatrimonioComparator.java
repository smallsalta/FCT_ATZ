package com.atz.comparator;

import java.util.Comparator;

import com.atz.persistencia.TMatrimonio;

public class TMatrimonioComparator 
implements Comparator<TMatrimonio>
{
	@Override
	public int compare(TMatrimonio o1, TMatrimonio o2) 
	{
		Integer io1	= Integer.valueOf( o1.getParte() );
		Integer io2	= Integer.valueOf( o2.getParte() );
		
		return io2.compareTo( io1 );
	}
}
