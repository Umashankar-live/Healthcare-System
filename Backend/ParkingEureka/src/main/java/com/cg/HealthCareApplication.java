package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Win10
 * we're using @SpringBootApplication as our primary application configuration class; behind the scenes, 
 * that's equivalent to @Configuration, @EnableAutoConfiguration, and @ComponentScan together .
 * 
 * @Configuration annotation is part of the spring core framework. Spring Configuration annotation indicates that the class has
 * @Bean definition methods ie. allows us to use annotations for dependency injection.
 *  
 * first step of defining Spring Beans is by adding the right annotation � @Component or @Service or @Repository.However, 
 * Spring does not know about the bean unless it knows where to search for it.This part of �telling Spring where to search� 
 * is called a @ComponentScan . we define the packages that have to be scanned.
 * 
 * @EnableAutoConfiguration tries to automatically configure our Spring application based upon the JAR dependency we have 
 * added in the classpath. eg. tomcat and SpringMVC are auto configured .

 * 
 */

@SpringBootApplication
@EnableEurekaServer  //makes spring boot  annotation acts as a Eureka Client
public class HealthCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareApplication.class, args);
	}

}
