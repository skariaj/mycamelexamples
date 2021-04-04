package com.skaria.camel.spring.amq;

import org.junit.rules.ExternalResource;

public class EmbeddedQPidBrokerRule extends ExternalResource{

	private EmbeddedQPidBroker broker;
	private String initialDefaultConfigLocation = "qpid-embedded-inmemory-configuration.json";
	
	public String getInitialDefaultConfigLocation() {
		return initialDefaultConfigLocation;
	}

	public void setInitialDefaultConfigLocation(String initialDefaultConfigLocation) {
		this.initialDefaultConfigLocation = initialDefaultConfigLocation;
	}

	@Override
	protected void before() throws Throwable {
		broker = new EmbeddedQPidBroker();
		broker.setInitialDefaultConfigLocation(initialDefaultConfigLocation);
		broker.start();
		
	}

	@Override
	protected void after() {
		broker.shutdown();
	}


}
