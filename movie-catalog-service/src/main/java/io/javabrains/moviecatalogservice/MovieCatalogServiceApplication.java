package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate gettemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getwebClientBulider() {
		return WebClient.builder();
	}
	/*
	 * cant have same type of bean as by default is bytype autowiring in spring boot
	 * 
	 * @Bean public RestTemplate getresttemplate() { return new RestTemplate(); }
	 */
}
