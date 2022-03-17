package com.zensar;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableHystrix
@EnableAutoConfiguration
public class SpringRestfulServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestfulServicesDemoApplication.class, args);
	}

	@Bean
	public Docket getCustomizeDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.zensar")).paths(PathSelectors.any()).build();
	}

	private ApiInfo getApiInfo() {

		return new ApiInfo("Employee Rest API Documentation", "Employee Status Update API", "2.4",
				"https://zensar.com/termsofservice",
				new Contact("Shradha", "http://Shradha.com", "shradha.pimpudkar@zenar.com"), "GPL", "http://gpl.com",
				new ArrayList<VendorExtension>());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
