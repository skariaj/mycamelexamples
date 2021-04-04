package com.skaria.camel.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeRestController {

	@RequestMapping(value="/somemapping", method = RequestMethod.GET, produces = "text/plain")
	String methodInRestController()
	{
		return "Some response from Rest Controller";
	}
}
