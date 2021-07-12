package com.mercadolibre.bootcampmelifrescos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact contact() {
        return new Contact("Squad 2 - Meli Bootcamp",
                "https://github.com/aelmo/bootcamp-meli-frescos",
                "mercadolivre.com");
    }

    private ApiInfoBuilder infoApi() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("REST Api - Meli Frescos");
        apiInfoBuilder.description("Api for Meli Bootcamp");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Private");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;
    }

    @Bean
    public Docket swaggerMeliFrescosApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rest-meliFrescos-api-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mercadolibre.bootcampmelifrescos.controller"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(this.infoApi().build())
                .useDefaultResponseMessages(false);
    }
}
