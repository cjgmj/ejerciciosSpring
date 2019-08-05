package com.cjgmj.di;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.cjgmj.di.domain.ItemFactura;
import com.cjgmj.di.domain.Producto;
import com.cjgmj.di.services.IndexService;
import com.cjgmj.di.services.impl.IndexServiceComplejoImpl;
import com.cjgmj.di.services.impl.IndexServiceImpl;

@Configuration
public class AppConfig {

	@Bean("indexService")
	public IndexService registrarMiSericio() {
		return new IndexServiceImpl();
	}

	@Bean("indexServiceComplejo")
	@Primary
	public IndexService registrarMiSericioComplejo() {
		return new IndexServiceComplejoImpl();
	}

	@Bean
	public List<ItemFactura> registrarItems() {
		Producto producto1 = new Producto("Camara Sony", 100.0);
		Producto producto2 = new Producto("Bicicleta Bianchi aro 26", 200.0);

		ItemFactura item1 = new ItemFactura(producto1, 2);
		ItemFactura item2 = new ItemFactura(producto2, 4);

		return Arrays.asList(item1, item2);
	}

}
