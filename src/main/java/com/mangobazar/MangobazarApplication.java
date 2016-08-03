package com.mangobazar;

import com.mangobazar.exception.CustomException;
import com.mangobazar.exception.ErrorCodes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

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

    /**
     * Adds new error_code attribute, so that client can show appropriate error message.
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
                                                          boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                // Customize the default entries in errorAttributes to suit your needs
                Throwable error = this.getError(requestAttributes);
                if (error != null && error instanceof CustomException){
                    errorAttributes.put("error_code", ((CustomException) error).getErrorCode());
                }else{
                    errorAttributes.put("error_code", ErrorCodes.ERROR_UNKNOWN);
                }

                return errorAttributes;
            }

        };
    }


}
