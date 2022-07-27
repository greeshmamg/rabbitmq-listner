package com.example.demo.rabbitmq.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMQConsumerProduct {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumerProduct.class);

	@RabbitListener(queues = "${rabbitmq.queue.product}")

	public void recievedMessage(Message message) {
		logger.info("Recieved Message From RabbitMQ- product: " + message);
		
	}
}
