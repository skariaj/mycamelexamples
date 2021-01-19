package com.skaria.camel.examples.contentbasedrouting;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ContentBasedRoutingExampleTest extends CamelTestSupport{

	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext context = super.createCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		return context;
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder()
				{

					@Override
					public void configure() throws Exception {
						from("file:src/data/inbox?noop=true")
						.log("hellooo......")
						.log("Received file: ${header.fileName}")
						.to("jms:incomingData")
						.to("mock:incomingdata");
						
						from("jms:incomingData")
						.choice()
							.when(header(Exchange.FILE_NAME).endsWith(".xml"))
								.to("jms:xmlData")
							.when(header(Exchange.FILE_NAME).regex("^.*(csv|csl)$"))
								.to("jms:csvData")
							.otherwise()
								.to("jms:otherData").stop()
						.end()		
						.to("jms:continueQueue");
						
						from("jms:xmlData")
						.log("Received xml message : ${header.CamelFileName}")
						.log("Received xml message : " + header(Exchange.FILE_NAME))
						.to("mock:xml");
						
					}
			
				};
	}
	
	@Test
	public void test1() throws Exception
	{
		System.out.println("Test1..........");
		getMockEndpoint("mock:incomingdata").expectedMessageCount(1);
		getMockEndpoint("mock:xml").expectedMessageCount(1);;
		assertMockEndpointsSatisfied();
	}
	
}
