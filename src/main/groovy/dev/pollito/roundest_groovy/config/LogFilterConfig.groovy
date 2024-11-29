package dev.pollito.roundest_groovy.config

import dev.pollito.roundest_groovy.filer.LogFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogFilterConfig {

    @Bean
    FilterRegistrationBean<LogFilter> loggingFilter() {
        def registrationBean = new FilterRegistrationBean<LogFilter>()
        registrationBean.filter = new LogFilter()
        registrationBean.addUrlPatterns("/*")
        return registrationBean
    }
}