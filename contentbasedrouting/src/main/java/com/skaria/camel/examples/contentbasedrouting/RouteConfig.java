package com.skaria.camel.examples.contentbasedrouting;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.jms.pool.PooledConnectionFactory;


import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.apache.camel.spring.javaconfig.CamelConfiguration;

@Configuration
@ComponentScan(basePackages = "com.skaria.camel.examples.contentbasedrouting")
public class RouteConfig extends  CamelConfiguration{
	
	@Value("activemq.uri")
	String activeMQUrl;
	
	
	@Bean
	public ConnectionFactory connectionFactory()
	{
		PooledConnectionFactory factory = new PooledConnectionFactory();
		factory.setMaxConnections(3);
		factory.setConnectionFactory(amqpConnectiofactory());
		return factory;
	}
	
	@Bean
	public ConnectionFactory amqpConnectiofactory()
	{
		ActiveMQConnectionFactory amqpConnectiofactory = new ActiveMQConnectionFactory();
		amqpConnectiofactory.setBrokerURL("vm://localhost?broker.persistent=false");
		return amqpConnectiofactory;
	}
	
	@Bean
	public JmsComponent jms()
	{
		JmsComponent component = JmsComponent.jmsComponentAutoAcknowledge(connectionFactory());
		component.setConnectionFactory(connectionFactory());
		component.setTransacted(true);
		return component;
		
	}



}
