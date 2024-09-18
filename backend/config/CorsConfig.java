package com.zbyszkobud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); 
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public OncePerRequestFilter disableChunkedTransferEncodingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws IOException, javax.servlet.ServletException {
    
                HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
                    @Override
                    public void setHeader(String name, String value) {
                        if ("Transfer-Encoding".equalsIgnoreCase(name)) {
                            System.out.println("Zmieniam Transfer-Encoding na identity");
                            value = "identity";
                        }
                        super.setHeader(name, value);
                    }
    
                    @Override
                    public void addHeader(String name, String value) {
                        if ("Transfer-Encoding".equalsIgnoreCase(name)) {
                            System.out.println("Dodaję Transfer-Encoding jako identity");
                            value = "identity";
                        }
                        super.addHeader(name, value);
                    }
                };
    
                System.out.println("Filtr ustawiający Transfer-Encoding na identity został wywołany dla: " + request.getRequestURI());
                filterChain.doFilter(request, responseWrapper);
            }
        };
    }
    
    
}