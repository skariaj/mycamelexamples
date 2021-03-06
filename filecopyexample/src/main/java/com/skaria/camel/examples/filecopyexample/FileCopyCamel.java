package com.skaria.camel.examples.filecopyexample;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopyCamel {
	
	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("file:data/inbox?noop=true").to("file:data/outbox");
				
			}
			
		});
		
		context.start();
		
		Thread.sleep(3000);
		
		context.stop();
	}

}
