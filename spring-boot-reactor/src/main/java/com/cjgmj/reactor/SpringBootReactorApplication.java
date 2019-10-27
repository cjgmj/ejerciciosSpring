package com.cjgmj.reactor;

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
		Flux<Usuario> nombres = Flux.just("Joe", "John", "Linus").map(nombre -> new Usuario(nombre.toUpperCase(), null))
				.doOnNext(usuario -> {
					if (usuario == null) {
						throw new RuntimeException("El nombre no puede estar vacío");
					}

					System.out.println(usuario.getNombre());
				}).map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				});

		nombres.subscribe(e -> LOG.info(e.toString()), error -> LOG.error(error.getMessage()), new Runnable() {
			@Override
			public void run() {
				LOG.info("Se ha completado la ejecución del observable");
			}
		});
	}

}
