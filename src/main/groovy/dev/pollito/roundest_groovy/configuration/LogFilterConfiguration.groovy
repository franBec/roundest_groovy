package dev.pollito.roundest_groovy.configuration

import dev.pollito.roundest_groovy.filter.LogFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogFilterConfiguration {

    @Bean
    FilterRegistrationBean<LogFilter> loggingFilter() {
        def registrationBean = new FilterRegistrationBean<LogFilter>()
        registrationBean.filter = new LogFilter()
        registrationBean.addUrlPatterns("/*")
        return registrationBean
    }
}