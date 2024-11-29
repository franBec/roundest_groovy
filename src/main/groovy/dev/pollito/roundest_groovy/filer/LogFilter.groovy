package dev.pollito.roundest_groovy.filer

import groovy.util.logging.Slf4j
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Slf4j
class LogFilter implements Filter {

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        logRequestDetails(servletRequest as HttpServletRequest)
        filterChain.doFilter(servletRequest, servletResponse)
        logResponseDetails(servletResponse as HttpServletResponse)
    }

    private void logRequestDetails(HttpServletRequest request) {
        log.info(
                ">>>> Method: ${request.method}; URI: ${request.requestURI}; QueryString: ${request.queryString}; Headers: ${headersToString(request)}"
        )
    }

    private String headersToString(HttpServletRequest request) {
        request.headerNames.collect { headerName ->
            "${headerName}: ${request.getHeader(headerName)}"
        }.join(', ', '{', '}')
    }

    private void logResponseDetails(HttpServletResponse response) {
        log.info("<<<< Response Status: ${response.status}")
    }
}