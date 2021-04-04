package com.skaria.camel.spring.amq;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.skaria.camel.spring.amq.AmqRoute;

public class AmqRouteTest extends CamelSpringTestSupport{

	@Override
	protected CamelContext createCamelContext() throws Exception {
		
		CamelContext camelContext = super.createCamelContext();
		
		return camelContext;
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		
		return new AmqRoute();
	}

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
		
		return springContext;
	}
	
	

}
