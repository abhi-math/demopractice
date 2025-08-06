package com.abhishek.demopractice.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);    // IP & session
        filter.setIncludeQueryString(true);   // ?param=value
        filter.setIncludeHeaders(true);       // All headers
        filter.setIncludePayload(true);       // Body content
        filter.setMaxPayloadLength(10000);    // Limit for body logging
        return filter;
    }
}
