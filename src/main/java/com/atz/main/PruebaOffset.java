package com.atz.main;

import java.util.Date;
import java.util.TimeZone;

public class PruebaOffset {

	public static void main(String[] args) 
	{
		TimeZone tz = TimeZone.getTimeZone("Europe/Oslo");
		long min	= tz.getOffset(new Date().getTime()) / 1000 / 60; 
		
		System.out.println(min);
	}

}
