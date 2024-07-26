package com.nagarro.accountmanagmentservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

	@RabbitListener(queues = {"${rabbitMQ.accountManagment.queue.name}"})
	public void consume(String message) {
		System.out.println("\\033[32m " + message);
	}
	
}
