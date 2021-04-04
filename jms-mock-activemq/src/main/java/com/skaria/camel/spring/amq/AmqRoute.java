package com.skaria.camel.spring.amq;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AmqRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration().component("undertow").port(8080);

		rest("/amq").get("hello").produces("text/plain").to("direct:foo");

		from("direct:foo").routeId("route-101")
		.setExchangePattern(ExchangePattern.InOut)
		.log(LoggingLevel.INFO, "inside camel route...")
		.setBody(constant("some body"))
		.to("jms:outgoingMsg");
		
		
		
		from("jms:outgoingMsg")
		.log("Received  message : ${body}")
		.log("Received  message : ${headers} " )
		.to("mock:endpoint");

		System.out.println("***************************************************");
		System.out.println("");
		System.out.println("  REST service");
		System.out.println("");
		System.out.println("  You can try calling this service using http");
		System.out.println("***************************************************");
	}

}
