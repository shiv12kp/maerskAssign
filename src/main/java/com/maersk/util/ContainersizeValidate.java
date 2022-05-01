package com.maersk.util;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ContainersizeValidator.class)
public @interface ContainersizeValidate {

	int[] acceptedValues();

	String message() default "Invalid Container size";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
