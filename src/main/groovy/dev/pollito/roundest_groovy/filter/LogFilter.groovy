package dev.pollito.roundest_groovy.filter

import groovy.util.logging.Slf4j
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(2)
@Slf4j
class LogFilter implements Filter {

	@Override
	void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		logRequestDetails(servletRequest as HttpServletRequest)
		filterChain.doFilter(servletRequest, servletResponse)
		logResponseDetails(servletResponse as HttpServletResponse)
	}

	private static void logRequestDetails(HttpServletRequest request) {
		log.info(
				">>>> Method: ${request.method}; URI: ${request.requestURI}; QueryString: ${request.queryString}; Headers: ${headersToString(request)}"
				)
	}

	private static String headersToString(HttpServletRequest request) {
		List<GString> headers = request.headerNames.collect { headerName ->
			"${headerName}: ${request.getHeader(headerName)}"
		}
		"{${headers.join(', ')}}"
	}


	private static void logResponseDetails(HttpServletResponse response) {
		log.info("<<<< Response Status: ${response.status}")
	}
}
