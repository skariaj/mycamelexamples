package com.skaria.camel.spring.amq;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.qpid.server.SystemLauncher;
import org.apache.qpid.server.configuration.IllegalConfigurationException;
import org.apache.qpid.server.model.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedQPidBroker implements AutoCloseable{
	
	public static final Logger logger = LoggerFactory.getLogger(EmbeddedQPidBroker.class);

	private String initialDefaultConfigLocation = "qpid-embedded-inmemory-configuration.json";
	
	private URL initialConfigUrl;
	private SystemLauncher systemLauncher ;
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public EmbeddedQPidBroker()
	{
		this.systemLauncher = new SystemLauncher();
	}
	
	public void shutdown()
	{
		systemLauncher.shutdown();
		logger.info("Broker stopped...");
	}
	
	public void start() throws Exception
	{
		systemLauncher.startup(createSystemConfig());
		logger.info("Broker started...");
		
	}

	public String getInitialDefaultConfigLocation() {
		return initialDefaultConfigLocation;
	}

	public void setInitialDefaultConfigLocation(String initialDefaultConfigLocation) {
		this.initialDefaultConfigLocation = initialDefaultConfigLocation;
	}

	private Map<String, Object> createSystemConfig() throws IllegalConfigurationException{
		
		Map<String, Object> systemAttributes = new HashMap<>();
		URL initialConfigUrl = this.initialConfigUrl;
		if (initialConfigUrl == null)
		{
			initialConfigUrl = EmbeddedQPidBroker.class.getClassLoader().getResource(initialDefaultConfigLocation);
			
		}
		if(initialConfigUrl == null)
		{
			throw new IllegalConfigurationException("Configuration location "+ this.initialDefaultConfigLocation + " is not found!");
			
		}
		
		systemAttributes.put(SystemConfig.TYPE, "Memory");
		systemAttributes.put(SystemConfig.INITIAL_CONFIGURATION_LOCATION, initialConfigUrl.toExternalForm());
		systemAttributes.put(SystemConfig.STARTUP_LOGGED_TO_SYSTEM_OUT, true);
		
		return systemAttributes;
	}

	
	public static void main(String[] args) {
        try(EmbeddedQPidBroker broker = new EmbeddedQPidBroker()) {
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
	
}
