package com.skaria.camel.examples.contentbasedrouting;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedRoute extends RouteBuilder{

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
		
	

}
