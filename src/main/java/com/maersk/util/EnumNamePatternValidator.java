package com.maersk.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, ContainerType> {
	private List<String> valueList ;

	@Override
	public void initialize(EnumNamePattern constraintAnnotation) {
		valueList = new ArrayList<String>();
		for (String val : constraintAnnotation.acceptedValues()) {
			valueList.add(val.toUpperCase());
		}
	}

	@Override
	public boolean isValid(ContainerType value, ConstraintValidatorContext context) {
		return valueList.contains(value.getContainerTypeEnum());
	}
}