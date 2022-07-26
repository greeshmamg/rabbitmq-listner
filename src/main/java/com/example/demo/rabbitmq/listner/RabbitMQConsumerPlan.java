package com.example.demo.rabbitmq.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.rabbitmq.model.OrderDetail;

@Component
public class RabbitMQConsumerPlan {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumerPlan.class);

	@RabbitListener(queues = "${rabbitmq.queue.plan}")
	public void recievedMessage(Message message) {
	
		logger.info("Recieved Message From RabbitMQ:plans " + message);

		 
	}
}
