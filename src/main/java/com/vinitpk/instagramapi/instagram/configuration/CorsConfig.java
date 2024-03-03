package com.vinitpk.instagramapi.instagram.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Vinit Kelginmane
 * @project: instagram-api-springboot
 * @Date: 12-02-2024
 */

@Configuration
public class CorsConfig {

    // Define a CorsFilter bean
    @Bean
    public CorsFilter corsFilter() {
        // Create a new CorsConfiguration object
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Set allowed origins (e.g., allowed domains)
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "https://instagram-clone-vinitpk.netlify.app"));

        // Set allowed HTTP methods
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));

        // Allow credentials such as cookies, authorization headers, etc.
        corsConfiguration.setAllowCredentials(true);

        // Set allowed headers
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        // Expose additional headers to the client
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        // Set the maximum age (in seconds) for which this CORS configuration is valid
        corsConfiguration.setMaxAge(3600L);

        // Create a UrlBasedCorsConfigurationSource and register the CorsConfiguration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        // Create and return a new CorsFilter instance with the configured CorsConfiguration
        return new CorsFilter(source);
    }
}
