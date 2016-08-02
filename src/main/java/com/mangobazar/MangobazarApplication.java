package com.mangobazar;

import static springfox.documentation.builders.PathSelectors.regex;

import com.fasterxml.classmate.TypeResolver;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import  springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.ArrayList;
import java.util.List;


@EnableSwagger2
@SpringBootApplication
public class MangobazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangobazarApplication.class, args);
	}
	
	
	@Bean
    public Docket MangobazarApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/.*"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Mangobazar REST Api")
                .description(null)
                .termsOfServiceUrl(null)
                .license(null)
                .licenseUrl(null)
                .version(null)
                .build();
    }


    // TODO added just to test JWT with springfox swagger ui
    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "app-client-id",
                "app-client-secret",
                "app-realm",
                "app",
                "",
                ApiKeyVehicle.HEADER,
                "X-AUTH-TOKEN",
                "," /*scope separator*/);
    }


}
