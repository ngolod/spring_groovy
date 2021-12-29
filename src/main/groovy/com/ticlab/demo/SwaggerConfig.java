package com.ticlab.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SwaggerConfig {

    @Bean
    public Docket configApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    // Describe your apis
    private ApiInfo apiInfo() {
 Contact contact = new Contact(
         "Lanfia D",
         "https://ngdia.tk/",
         "lanfiadiarra@gmail.com"
 );       
        return new ApiInfoBuilder()
                .title("TRAINING APIs")
                .description("")
                .version("1.0")
                .contact(contact)
                .license("@copyright 2021")
                .build();
    }

}