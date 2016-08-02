package com.mangobazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class MangobazarApplication {

    /**
     * Main entry point of the Spring boot application.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * None.
     */
    public static void main(String[] args) {
        SpringApplication.run(MangobazarApplication.class, args);
    }


    /**
     * Swagger API documentation configuration.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * Need to find a way to make it private.
     */
    @Bean
    public Docket MangobazarApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/.*"))
                .build();
    }


    /**
     * Swagger API documentation configuration.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * Need to find a way to make it private.
     */
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


    /**
     * Swagger API documentation configuration.
     * <p>
     * NOTES/ASSUMPTIONS: <BR>
     * TODO added just to pass JWT token in the header with springfox swagger ui
     */
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
