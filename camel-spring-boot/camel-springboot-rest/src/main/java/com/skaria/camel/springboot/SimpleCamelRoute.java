package com.skaria.camel.springboot;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleCamelRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:food")
		.log("inside camel route.....")
		.setBody(simple("setting the exchange body"))
		.log("1......simple(${body})")
		.setBody(constant("another body being set"))
		.log("2......simple(${body})");
		
		
	}

	
}
