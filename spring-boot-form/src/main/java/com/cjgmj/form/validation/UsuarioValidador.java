package com.cjgmj.form.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cjgmj.form.models.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		final Usuario usuario = (Usuario) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "requerido.usuario.username");

//		if (!usuario.getIdentificador().matches("[0-9]{8}[-]?[A-Z]{1}")) {
//			errors.rejectValue("identificador", "Pattern.usuario.identificador");
//		}
	}

}
