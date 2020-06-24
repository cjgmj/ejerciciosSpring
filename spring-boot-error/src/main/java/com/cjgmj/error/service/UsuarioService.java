package com.cjgmj.error.service;

import java.util.List;

import com.cjgmj.error.models.Usuario;

public interface UsuarioService {

	List<Usuario> listar();

	Usuario obtenerPorId(Integer id);

}
