package com.skaria.camel.spring.amq;

import org.apache.camel.main.Main;

public class MainCamelSpringApplication {
	
	public static void main(String[] args) {
		
		Main main = new Main();
		System.out.println("inside main..");
		try {
			main.configure().addConfigurationClass(RouteConfig.class);
			main.configure().addRoutesBuilder(AmqRoute.class);
			main.run(args);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
