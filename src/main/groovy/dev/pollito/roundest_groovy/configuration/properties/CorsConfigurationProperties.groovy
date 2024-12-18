package dev.pollito.roundest_groovy.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cors")
class CorsConfigurationProperties {
	List<String> allowedOrigins
	List<String> allowedMethods
	String allowedHeaders
	Boolean allowCredentials
}
