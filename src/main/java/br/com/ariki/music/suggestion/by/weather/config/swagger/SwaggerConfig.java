package br.com.ariki.music.suggestion.by.weather.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		 return new Docket(DocumentationType.SWAGGER_2)          
			      .select()
			      .apis(RequestHandlerSelectors.basePackage("br.com.ariki.music.suggestion.by.weather.gateway.controller"))
			      .paths(PathSelectors.ant("/suggestion/*"))
			      .build()
			      .apiInfo(metadata());
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Sugestões de playlist para o seu clima")
				.description("\"Dependendo do seu clima enviamos sugestões de playlista para você\"").version("1.0.0")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

}
