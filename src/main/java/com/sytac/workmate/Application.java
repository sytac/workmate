package com.sytac.workmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The Class Application.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

}