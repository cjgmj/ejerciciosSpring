package com.cjgmj.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

}
