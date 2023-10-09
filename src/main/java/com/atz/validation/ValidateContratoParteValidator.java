package com.atz.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.atz.fb.ContratosFb;


@Component
public class ValidateContratoParteValidator implements ConstraintValidator<ValidateContratoParte, ContratosFb>{

	List<Integer> agentesExtintor = Arrays.asList(new Integer[] {1, 2});
	List<Integer> agentesBie = Arrays.asList(new Integer[] {3002, 3004});
	List<Integer> agentesCentralita = Arrays.asList(new Integer[] {4, 5, 3001, 3005, 3010});
	
	
	@Override
	public boolean isValid(ContratosFb value, ConstraintValidatorContext context) {
		
		Boolean esExtintor = Arrays.asList(value.getAgentesExt()).stream().allMatch(x -> agentesExtintor.contains(x));
		Boolean esBie = Arrays.asList(value.getAgentesExt()).stream().allMatch(x -> agentesBie.contains(x));
		Boolean esCentralita = Arrays.asList(value.getAgentesExt()).stream().allMatch(x -> agentesCentralita.contains(x));
		
		
		return esExtintor ^ esBie ^ esCentralita;
	}

	@Override
	public void initialize(ValidateContratoParte constraintAnnotation) {

	}

}
