package dev.pollito.roundest_groovy.configuration

import dev.pollito.roundest_groovy.configuration.properties.CorsConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration implements WebMvcConfigurer{
	private final CorsConfigurationProperties corsConfigurationProperties

	CorsConfiguration(CorsConfigurationProperties corsConfigurationProperties){
		this.corsConfigurationProperties = corsConfigurationProperties
	}

	@Override
	void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/**")
				.allowedOrigins(corsConfigurationProperties.allowedOrigins.toArray(new String[0]))
				.allowedMethods(corsConfigurationProperties.allowedMethods.toArray(new String[0]))
				.allowedHeaders(corsConfigurationProperties.allowedHeaders)
				.allowCredentials(corsConfigurationProperties.allowCredentials)
	}
}
