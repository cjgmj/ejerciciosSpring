package com.cjgmj.error.errors;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 4399015624831062757L;

	public UsuarioNoEncontradoException(String id) {
		super("Usuario con ID: ".concat(id).concat(" no existe en el sistema"));
	}

}
