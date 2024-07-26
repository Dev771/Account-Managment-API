package com.nagarro.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;
import com.nagarro.apigateway.dto.HeaderResponse;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
    public GatewayFilter apply(Config config) {
		
		System.out.println("Hello");
		
        return ((exchange, chain) -> {
        	System.out.println(validator.isSecured.test(exchange.getRequest()));
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                	System.out.println(exchange.getRequest().getHeaders());
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                	
                	System.out.println(authHeader);

                	
                	webClientBuilder.build().get()
                		.uri("http://apigateway:8080/auth/Customer/Validator/"+ authHeader)
                		.retrieve()
                		.bodyToMono(HeaderResponse.class)
                		.subscribe(
            				result -> {
                            	if(!result.isStatus()) {
                                    System.out.println("invalid access...!1");
                                    throw new RuntimeException("un authorized access to application");
                            	}
            					
        				});
                	

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application" + e);
                }
            }
            return chain.filter(exchange);
        });
    }

	public static class Config {
		
	}
}