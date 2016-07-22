package com.sytac.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * The Class Config.
 */
@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
public class Config {

	/** The environment. */
	@Resource
	private Environment environment;


}