package com.nagarro.customermanagmentservice.Producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

	@Value("${rabbitMQ.exchange.name}")
	private String exchange;
	
	@Value("${rabbitMQ.customerAuthorization.routing.key}")
	private String customerAuthoriuzationRoutingKey;
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {

		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(String message) {
		
		System.out.println("\\033[32m Sending Data Successfully: " + message);
		
		rabbitTemplate.convertAndSend(exchange, customerAuthoriuzationRoutingKey, message);
	}
	
	
}
