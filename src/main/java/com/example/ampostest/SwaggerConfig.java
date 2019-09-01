package com.example.ampostest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact DEFAULT_CONTACT = new Contact("", "", "") ;
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
													.title("Api Documenttation")
													.description("Api Documenttation")
													.version("1.0")
													.contact(DEFAULT_CONTACT)
													.license("Apache 2.0")
													.licenseUrl("http://www.apache.org/license/LICENSE-2.0")
													.build();
	
	private static final Set<String> DEFAULT_PRODUCES_AND_SONSUMES = new HashSet<String>(
										Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_SONSUMES);
		
	}
}
