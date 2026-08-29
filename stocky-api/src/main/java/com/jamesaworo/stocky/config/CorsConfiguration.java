package com.jamesaworo.stocky.config;

import com.jamesaworo.stocky.config.converter.LocalDateStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		        .allowedOrigins("http://localhost:4200")
		        .allowedMethods("GET", "POST", "PUT", "DELETE")
		        .allowedHeaders("*")
		        .allowCredentials(false)
		        .maxAge(3600);
	}


	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new LocalDateStringConverter());
	}
}
