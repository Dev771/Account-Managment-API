package com.nagarro.customerauthorization.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

	@RabbitListener(queues = {"${rabbitMQ.customerAuthorization.queue.name}"})
	public void consume(String message) {
		System.out.println("\\033[32m Message Recieved: " + message);
	}
	
}
