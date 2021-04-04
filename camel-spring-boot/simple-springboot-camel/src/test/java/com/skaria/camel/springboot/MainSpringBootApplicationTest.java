package com.skaria.camel.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainSpringBootApplicationTest {

	@Test
	public void test1()
	{
		System.out.println("inside spring boot camel route test...........");
	}
}
