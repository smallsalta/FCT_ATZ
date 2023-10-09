package com.atz.util;

import java.util.Arrays;
import java.util.List;

import com.atz.fb.ContratosFb;

public class Utils {
	
	public static Integer detectTipoParte(ContratosFb fb) {
		List<Integer> agentesExtintor = Arrays.asList(new Integer[] {1, 2});
		List<Integer> agentesBie = Arrays.asList(new Integer[] {3002, 3004});
		List<Integer> agentesCentralita = Arrays.asList(new Integer[] {4, 5, 3001, 3005, 3010});
		Integer res = 0;
		
		if(Arrays.asList(fb.getAgentesExt()).stream().allMatch(x -> agentesExtintor.contains(x))) {
			res = 1;
		} else if(Arrays.asList(fb.getAgentesExt()).stream().allMatch(x -> agentesBie.contains(x))) {
			res = 2;
		} else if(Arrays.asList(fb.getAgentesExt()).stream().allMatch(x -> agentesCentralita.contains(x))) {
			res = 3;
		}
		
		return res;
	}
	 

}
