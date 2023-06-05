package com.turkcellcamp.commonpackage.configuration.tracing;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {

    @Bean
    public Capability capability(MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}
