package com.nagarro.customermanagmentservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.nagarro.customermanagmentservice.dto.CustomerAccountDetailsEntity;

@Service
public class RabbitMQConsumer {

	@RabbitListener(queues = {"${rabbitMQ.customerManagment.queue.name}"})
	public void consume(CustomerAccountDetailsEntity accountDetailsEntity) {
		System.out.println("\\033[32m Data Recieved: " + accountDetailsEntity);
	}
	
}
