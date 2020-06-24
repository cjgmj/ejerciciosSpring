package com.cjgmj.error.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cjgmj.error.models.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;

	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();

		this.lista.add(new Usuario(1, "Joe", "Simons"));
		this.lista.add(new Usuario(2, "John", "Doe"));
		this.lista.add(new Usuario(3, "Linus", "Torvalds"));
		this.lista.add(new Usuario(4, "Jane", "Doe"));
		this.lista.add(new Usuario(5, "Rasmus", "Lerdorf"));
		this.lista.add(new Usuario(6, "Erich", "Gamma"));
		this.lista.add(new Usuario(7, "Richard", "Helm"));
	}

	@Override
	public List<Usuario> listar() {
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario usuario = null;

		for (final Usuario u : this.lista) {
			if (u.getId().equals(id)) {
				usuario = u;
				break;
			}
		}

		return usuario;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		final Usuario usuario = this.obtenerPorId(id);

		return Optional.ofNullable(usuario);
	}

}
