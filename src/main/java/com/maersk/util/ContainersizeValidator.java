package com.maersk.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainersizeValidator implements ConstraintValidator<ContainersizeValidate, Integer> {

	private int[] values;

	@Override
	public void initialize(ContainersizeValidate constraintAnnotation) {
		values = constraintAnnotation.acceptedValues();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return values[0]==value||values[1]==value;
	}

}
