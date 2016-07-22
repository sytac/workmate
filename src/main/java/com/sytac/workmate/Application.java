package com.sytac.workmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class Application.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	
	@Bean
	public  AbstractRepositoryPopulatorFactoryBean repositoryPopulator() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
		factoryBean.setResources(new Resource[] { new ClassPathResource("workplaces.json") });
		factoryBean.setMapper(mapper);

		return factoryBean;
	}
	
	static abstract class GeoJsonPointMixin {
		GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {}
	}
	
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