package com.maersk.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeStampValidator implements ConstraintValidator<TimestampValidate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			java.time.format.DateTimeFormatter.ISO_DATE_TIME.parse(value);
			return true;
		} catch (java.time.format.DateTimeParseException e) {
			return false;
		}

	}

}
