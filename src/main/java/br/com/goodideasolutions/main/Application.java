package br.com.goodideasolutions.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import br.com.goodideasolutions.entity.User;
import br.com.goodideasolutions.repository.UserRepository;

@EnableSwagger
@SpringBootApplication
@EnableJpaRepositories("br.com.goodideasolutions.repository")
@EntityScan("br.com.goodideasolutions.entity")
@ComponentScan(basePackages = {"br.com.goodideasolutions"})
@Configuration
@EnableAutoConfiguration
public class Application {
    
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
    private SpringSwaggerConfig swaggerConfig;
	
    public static void main(String[] args) {
           new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
    
    @Bean
	public CommandLineRunner demo(final UserRepository repository) {
		return (args) -> {
			repository.save(new User("andrefelipecp", "123"));
			repository.save(new User("goodidea", "123"));
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
            log.info("");
		};
	}
    
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
