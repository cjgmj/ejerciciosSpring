package com.cjgmj.datajpa;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private final static Logger LOG = LoggerFactory.getLogger(MvcConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);

		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

		LOG.info("Ruta del archivo: " + resourcePath);

		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
	}

}
