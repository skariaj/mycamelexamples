package com.skaria.camel.examples.contentbasedrouting;

import javax.jms.ConnectionFactory;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spi.Registry;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;


public class SpringContentBasedRoutingExampleTest extends CamelSpringTestSupport{


	@Override
	protected AbstractApplicationContext createApplicationContext() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(RouteConfig.class);

		return annotationConfigApplicationContext;
	}

	@Test
	public void test1() throws Exception
	{
		System.out.println("Test1..........");
		getMockEndpoint("mock:incomingdata").expectedMessageCount(1);
		getMockEndpoint("mock:xml").expectedMessageCount(1);;
		assertMockEndpointsSatisfied();
	}

	@Override
	protected void bindToRegistry(Registry registry) throws Exception {
		JmsComponent jms = (JmsComponent)applicationContext.getBean("jms");
		registry.bind("jms", jms);
	}
}
