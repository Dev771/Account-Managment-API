package com.nagarro.accountmanagmentservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarro.accountmanagmentservice.dto.AccountDetailsEntity;

@Service
public class RabbitMQProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	@Value("${rabbitMQ.exchange.name}")
	private String exchange;
	
	@Value("${rabbitMQ.customerManagment.routing.key}")
	private String customerRoutingKey;

	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(AccountDetailsEntity accountDetailsEntity) {
		
		logger.info(String.format("\\033[32m Message Sent : Data Sent SuccessFully %s", accountDetailsEntity));
		
		rabbitTemplate.convertAndSend(exchange, customerRoutingKey, accountDetailsEntity);
	}
	
	
}
