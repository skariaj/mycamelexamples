package com.skaria.camel.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleCamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		rest("/").produces("text/plain").get("hello").to("direct:foo");

		from("direct:foo").log("inside camel route.....").setBody(simple("setting the exchange body"))
				.setBody(constant("another body being set"))
				.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.http.common.HttpMethods.GET))
				.to("http://localhost:8089/resource/path?bridgeEndpoint=true");

	}

}
