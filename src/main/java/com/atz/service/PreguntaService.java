package com.atz.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atz.comparator.TPreguntasComp;
import com.atz.dao.PreguntasDAO;
import com.atz.persistencia.TPreguntas;
import com.atz.persistencia.TPreguntasRespuestas;

@Service
public class PreguntaService 
{
	@Autowired
	private PreguntasDAO p;
	
	/**
	 * ABC (1) = CO2 (2)
	 * BIE 25 (3002) = BIE 45 (3004)
	 * @param agt
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<TPreguntas> getPreguntas(Set<Integer> agt)
	{
		List<Integer> ex1 	= Arrays.asList(1, 2);
		List<Integer> ex2 	= Arrays.asList(3002, 3004);
		List<TPreguntas> lp	= new LinkedList<>();
		
		if( agt.containsAll(ex1) )
		{
			agt.remove( new Integer(1) );
		}
		
		if( agt.containsAll(ex2) )
		{
			agt.remove( new Integer(3002) );
		}
		
		agt.forEach( t -> lp.addAll( this.p.getQuestions(t) ) );
		
		Collections.sort( lp, new TPreguntasComp() );
		
		return lp;
	}
	
	@Transactional(readOnly=true)
	public List<TPreguntasRespuestas> getRespuestas()
	{
		return this.p.getAnswers();
	}
	
	@Transactional(readOnly=true)
	public List<TPreguntas> getPreguntas()
	{
		return this.p.getQuestions();
	}
}
