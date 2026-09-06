package com.jamesaworo.stocky.configuration.configurer;

import com.jamesaworo.stocky.configuration.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Aworo James
 * @since 5/13/23
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Value("${app.cors.allowed-origins:http://localhost:4200}")
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		        .allowedOrigins(allowedOrigins)
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