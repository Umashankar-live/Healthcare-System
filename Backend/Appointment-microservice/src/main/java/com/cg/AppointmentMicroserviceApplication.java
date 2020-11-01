package com.cg;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableEurekaClient
@EnableSwagger2
public class AppointmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentMicroserviceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.cg")).build().apiInfo(myApiInfo());
	}

	private ApiInfo myApiInfo() {
		// for version 2.9.1
		ApiInfo apiInfo = new ApiInfo("SPRING WITH SWAGGER API", "API CREATION", "1.0", "Free to Use",
				new Contact("Yash Pawar", "/", "yash.pawar642@gmail.com"), "API licence", "/",
				Collections.emptyList());
		return apiInfo;
	}
	
	@Bean
	//@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
