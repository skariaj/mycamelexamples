package com.skaria.camel.springboot;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camelspring")
public class SomeRestController {
	
	@EndpointInject(value = "direct:food")
	private FluentProducerTemplate producer;
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET, produces = "text/plain")
	public String someMethod()
	{
		String response = producer.request(String.class);
		return "some response from rest contoller" + response;
	}
	
	
}
