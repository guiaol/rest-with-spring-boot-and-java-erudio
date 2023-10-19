package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	/*
	 * é um objeto que é instanciado, montado e gerenciado pelo spring IOC container.
	 * O spring IOC container busca as informacoes em xmls, annotations ou em codigo java sobre como os benas devem ser instanciados
	 * configurados e montados e como se relacionam como outros beans.
	 * Isso ocorre pela injecao de dependencia.
	 * Podemos criar beans como @Component, @configuration, @Service, @Repository que são gerenciados pelo Srpring.
	 * 
	 **/
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java 17 Spring Boot 3")
						.version("v1")
						.description("Some description about your API")
						.termsOfService("https://gmail.com")
						.license(new License().name("Apache 2.0").url("https://gmail.com")));
	}
	
}
