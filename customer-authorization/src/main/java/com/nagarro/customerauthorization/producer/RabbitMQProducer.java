package com.nagarro.customerauthorization.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducer {

	@Value("${rabbitMQ.exchange.name}")
	private String exchange;
	
	@Value("${rabbitMQ.accountManagment.routing.key}")
	private String accountRoutingKey;

	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(String message) {
		
		System.out.println("\\033[32m Data Sent Successfully: " + message);
		
		rabbitTemplate.convertAndSend(exchange, accountRoutingKey, message);
	}
	
}
