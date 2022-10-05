package com.quiz.quizsystem;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
public class QuizsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizsystemApplication.class, args);
	}

  @Bean
  protected BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(12);
  }

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        
				corsConfiguration.setAllowedHeaders(Arrays.asList(
								"Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        
				corsConfiguration.setExposedHeaders(Arrays.asList(
						"Origin", "Content-Type", "Accept", 
						"Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
						 "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
				);

        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
				
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
