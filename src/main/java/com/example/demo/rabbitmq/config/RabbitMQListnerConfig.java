package com.example.demo.rabbitmq.config;

import java.util.Optional;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.demo.rabbitmq.listner.RabbitMQConsumerPlan;
import com.example.demo.rabbitmq.listner.RabbitMQConsumerProduct;

@Service
public class RabbitMQListnerConfig {

	@Value("${rabbitmq.exchange}")
	String exchange;

	/*
	 * public final CachingConnectionFactory connectionFactory;
	 * 
	 * public RabbitMQConfig(CachingConnectionFactory connectionFactory) {
	 * this.connectionFactory = connectionFactory; }
	 */
	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter, ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}
	@Bean(name = "rabbitListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);

		factory.setAfterReceivePostProcessors(new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				String type = message.getMessageProperties().getHeaders().get("TYPE").toString();
				String typeId = null;

				if (type.equalsIgnoreCase("PLAN")) {
					typeId = RabbitMQConsumerPlan.class.getName();
				} else if (type.equalsIgnoreCase("PRODUCT")) {
					typeId = RabbitMQConsumerProduct.class.getName();
				}

				Optional.ofNullable(typeId).ifPresent(t -> message.getMessageProperties().setHeader("__TypeId__", t));

				return message;
			}

		});

		return factory;
	}


	/*
	 * @Bean public AmqpTemplate rabbitTemplate(Jackson2JsonMessageConverter
	 * converter) { RabbitTemplate rabbitTemplate = new
	 * RabbitTemplate(connectionFactory);
	 * rabbitTemplate.setMessageConverter(converter()); return rabbitTemplate; }
	 */}
