package com.skaria.camel.spring.amq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.BindToRegistry;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.skaria.camel.spring.amq"})
public class RouteConfig extends CamelConfiguration{

	@Bean
	@BindToRegistry
	public ConnectionFactory connectionFactory()
	{
		PooledConnectionFactory connectionFactory = new PooledConnectionFactory();
		connectionFactory.setMaxConnections(3);
		connectionFactory.setConnectionFactory(amqpConnectionFactory());
		return connectionFactory;
	}

	@Bean
	@BindToRegistry
	public Object amqpConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("vm://localhost?broker.persistent=false");
		return activeMQConnectionFactory;
		
	}
	
	@Bean
	@BindToRegistry
	public JmsComponent jms()
	{
		JmsComponent component = JmsComponent.jmsComponentAutoAcknowledge(connectionFactory());
		component.setTransacted(true);
		return component;
	}
}
