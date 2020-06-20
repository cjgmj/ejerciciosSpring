package com.cjgmj.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.matches("[0-9]{8}[-]?[A-Z]{1}")) {
			return true;
		}

		return false;
	}

}
