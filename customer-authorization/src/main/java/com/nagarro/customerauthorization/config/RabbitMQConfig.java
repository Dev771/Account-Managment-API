package com.nagarro.customerauthorization.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitMQ.exchange.name}")
	private String exchange;
	
	@Value("${rabbitMQ.customerAuthorization.queue.name}")
	private String queue;
	
	@Value("${rabbitMQ.customerAuthorization.routing.key}")
	private String bindingKey;
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(bindingKey);
	}

}
