package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serialization.converter.YamlJacksonToHttpMessegerConverter;

/**
 * Esta anotacao diz ao springboot que quanto estiver subindo o contexto (a
 * aplicacao), ele precisa ler esta classe que nela vai encontrar configuracoes
 * sobre o comportamento da aplicacao
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		// aceita parametros
//		configurer.favorParameter(true)
		// nome do parametro
//		.parameterName("mediaType")
		// ignora parametros no header
//		.ignoreAcceptHeader(true)
//		.useRegisteredExtensionsOnly(false)
//		.defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML);

		// aceita parametros
		configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML)
		.mediaType("xml", MediaType.APPLICATION_XML);

		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJacksonToHttpMessegerConverter());
	}

}
