package br.com.goodideasolutions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@EnableSwagger
public class SwaggerConfig {
	
	@Autowired
    private SpringSwaggerConfig swaggerConfig;
	
	@Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
           .apiInfo(apiInfo())
           .includePatterns("/users.*?")
           .swaggerGroup("admin");
    }
    
    private ApiInfo apiInfo() {
       ApiInfo apiInfo = new ApiInfo(
             "Swagger With Spring Boot",
             "This is a simple application to demonstrate how to work with Swagger in Spring Boot project!",
             "Free to use and mess around",
             "erudio@gmail.com",
             "Open Licence",
             "myemail@gmail.com"
       );
       return apiInfo;
    }
}
