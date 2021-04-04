package com.skaria.camel.spring.amq;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmqRouteTest extends CamelTestSupport{

	public final static Logger logger = LoggerFactory.getLogger(AmqRouteTest.class);
	@ClassRule
	public static EmbeddedQPidBrokerRule qpidBrokerRule = new EmbeddedQPidBrokerRule();
	
	
	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext context = super.createCamelContext();
		
		return context;
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		
		return new AmqRoute();
	}

	/*
	 * @Override protected AbstractApplicationContext createApplicationContext() {
	 * AnnotationConfigApplicationContext springContext = new
	 * AnnotationConfigApplicationContext();
	 * 
	 * return springContext; }
	 */
	
	
	@Test
	public void testHappyPath() throws Exception {
		System.out.println("inside test happy path...");

		AdviceWith.adviceWith(context, "route-101", route ->
		{
			//route.mockEndpoints();
			route.weaveAddLast().to("mock:testendpoint");
		});

		MockEndpoint mockEndpoint = getMockEndpoint("mock:testendpoint");
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.expectedBodiesReceived("test request body");
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		producerTemplate.
				sendBody("http://0.0.0.0:8080/amq/hello", "test request body");
		Thread.sleep(1000);
		assertMockEndpointsSatisfied();
		
	}
	

}
