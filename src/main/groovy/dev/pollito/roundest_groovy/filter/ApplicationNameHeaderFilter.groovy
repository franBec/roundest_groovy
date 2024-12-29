package dev.pollito.roundest_groovy.filter

import dev.pollito.roundest_groovy.configuration.properties.SpringApplicationConfigurationProperties
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class ApplicationNameHeaderFilter implements Filter {

	private final SpringApplicationConfigurationProperties springApplicationConfigurationProperties

	ApplicationNameHeaderFilter(SpringApplicationConfigurationProperties springApplicationConfigurationProperties){
		this.springApplicationConfigurationProperties = springApplicationConfigurationProperties
	}

	void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		if (response instanceof HttpServletResponse) {
			HttpServletResponse httpServletResponse = response as HttpServletResponse
			httpServletResponse.addHeader("X-Application-Name", springApplicationConfigurationProperties.name)
		}
		chain.doFilter(request, response)
	}
}
