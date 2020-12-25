package com.de.merck.xmlparsing.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @ApplicationScope
    public Counter applicationCounter() {
        return Metrics.counter("XmlParsingApplicationCounter");
    }
}
