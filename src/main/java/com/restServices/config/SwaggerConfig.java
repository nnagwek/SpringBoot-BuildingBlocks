package com.restServices.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.restServices"))
				.paths(PathSelectors.ant("/users/**")).build();
	}
	
	// Swagger Metadata  : http://localhost:8080/v2/api-docs
	// Swagger UI URL  : http://localhost:8080/swagger-ui.html

	@Bean
	public LinkDiscoverers discovers() {    
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	} 
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Niket's User Management Service")
				.description("This page list all apis of User Management Service ")
				.version("1.0")
				.contact(new Contact("Niket Nagwekar", "https://github.com/nnagwek/SpringBoot-BuildingBlocks/", "niket1409@gmail.com"))
				.license("License 1.0")
				.licenseUrl("https://github.com/nnagwek/SpringBoot-BuildingBlocks/")
				.build();
	}
}
