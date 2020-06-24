package com.cjgmj.error.service;

import java.util.List;
import java.util.Optional;

import com.cjgmj.error.models.Usuario;

public interface UsuarioService {

	List<Usuario> listar();

	Usuario obtenerPorId(Integer id);

	Optional<Usuario> obtenerPorIdOptional(Integer id);

}
