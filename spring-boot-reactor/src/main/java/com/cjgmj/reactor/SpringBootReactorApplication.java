package com.cjgmj.reactor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cjgmj.reactor.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Flux<String> nombres = Flux.just("Joe Simons", "John Doe", "Linus Torvalds", "Jane Doe", "Rasmus Lerdorf",
//				"Erich Gamma", "Richard Helm");

		List<String> listaNombres = new ArrayList<>();
		listaNombres.add("Joe Simons");
		listaNombres.add("John Doe");
		listaNombres.add("Linus Torvalds");
		listaNombres.add("Jane Doe");
		listaNombres.add("Rasmus Lerdorf");
		listaNombres.add("Erich Gamma");
		listaNombres.add("Richard Helm");

		Flux<String> nombres = Flux.fromIterable(listaNombres);

		// Los Observables son inmutables, no se varía la instancia del objeto original
		Flux<Usuario> usuarios = nombres
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> usuario.getApellido().toLowerCase().equals("doe")).doOnNext(usuario -> {
					if (usuario == null) {
						throw new RuntimeException("El nombre no puede estar vacío");
					}

					System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
				}).map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				});

		usuarios.subscribe(e -> LOG.info(e.toString()), error -> LOG.error(error.getMessage()), new Runnable() {
			@Override
			public void run() {
				LOG.info("Se ha completado la ejecución del observable");
			}
		});
	}

}
