package com.es.spaceship.sale;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpaceshipSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceshipSaleApplication.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("SpaceshipSale")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.apis(input ->
						input.findControllerAnnotation(Api.class)
								.transform(api -> !api.hidden())
								.or(Boolean.FALSE))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SpaceshipSale")
				.description("service identify other service \nhttp://localhost:8080/h2-console")
				.version("1.0.0-SNAPSHOT")
				.build();
	}
}
