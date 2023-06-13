package com.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//Check this Line123
@SpringBootApplication
//@EntityScan("com.project.springboot.bean")
public class PathLabApplication {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(PathLabApplication.class, args);
	}

}
