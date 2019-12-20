package com.makeathon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact("Deepak Kumar", 
		  "https://github.com/deepak-mohan", "deepak.mohna@infosys.com");
  
  @SuppressWarnings("rawtypes")
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Campaign Management System", 
		  "Awesome API Description", "1.0",
		  "urn:tos", DEFAULT_CONTACT, "Licensing not available", "n/a", new ArrayList<VendorExtension>());

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
      new HashSet<String>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE));

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
  }
}