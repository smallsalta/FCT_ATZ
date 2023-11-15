package com.atz.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = ValidateContratoParteValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateContratoParte {
	
	String message() default "Contrato inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
