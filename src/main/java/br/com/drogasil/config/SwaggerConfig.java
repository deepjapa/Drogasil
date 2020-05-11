package br.com.drogasil.config;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.WildcardType;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
    @Autowired
    private TypeResolver typeResolver;
		
	@Bean
    public Docket greetingApi() { 
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.drogasil.controller"))
        .paths(PathSelectors.any()).build().pathMapping("/")
        .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
        .alternateTypeRules(newRule(
                typeResolver.resolve(DeferredResult.class,
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class)))
        .useDefaultResponseMessages(true)
        .apiInfo(apiInfo());
	}
	
	

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

    private ApiInfo apiInfo() {
        return new ApiInfo("Drogasil", "Atividades", "V1.0.0", "",
                new Contact("Thiago Martins Hiroyshi", null, "deepjapa1@gmail.com"), "", "");
    }
	
}	


