package com.lottery.lotteryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
@Configuration
public class LotteryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryAppApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lottery.lotteryapp"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Lottery App API",
				"Lottery Application Software",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Nripendra Chauhan","http:/google.com","nk_chauhan@live.in"),
				"API License",
				"#",
				Collections.emptyList()
		);
	}
}
