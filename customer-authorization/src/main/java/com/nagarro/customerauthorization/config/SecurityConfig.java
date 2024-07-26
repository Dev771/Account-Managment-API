package com.nagarro.customerauthorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nagarro.customerauthorization.security.JWTAuthenticationEntryPoint;
import com.nagarro.customerauthorization.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
    private JWTAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        	.cors(cors -> cors.disable())
        	.authorizeHttpRequests(
        			auth -> auth.requestMatchers("/auth/Customer/**").permitAll()
        						.requestMatchers("auth/Customer/Validator/**").permitAll()
								.anyRequest().authenticated()
        	)
        	.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

}
