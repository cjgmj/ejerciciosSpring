package com.cjgmj.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Flux<String> nombres = Flux.just("Joe", "John", "Linus").doOnNext(e -> {
			if (e.isEmpty()) {
				throw new RuntimeException("El nombre no puede estar vacío");
			}

			System.out.println(e);
		});

		nombres.subscribe(e -> LOG.info(e), error -> LOG.error(error.getMessage()), new Runnable() {

			@Override
			public void run() {
				LOG.info("Se ha completado la ejecución del observable");
			}
		});
	}

}
