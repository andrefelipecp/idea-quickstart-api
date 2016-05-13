package br.com.goodideasolutions.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.goodideasolutions.entity.User;
import br.com.goodideasolutions.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories("br.com.goodideasolutions.repository")
@EntityScan("br.com.goodideasolutions.entity")
public class Application {
    
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
    public static void main(String[] args) {
           new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
    
    @Bean
	public CommandLineRunner demo(final UserRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new User("andrefelipecp", "123"));
			repository.save(new User("goodidea", "123"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
            log.info("");

			// fetch an individual customer by ID
            User user = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
            log.info("");

			// fetch customers by last name
			log.info("Customer found with findByUsername('andrefelipecp'):");
			log.info("--------------------------------------------");
			for (User userFind : repository.findByUsername("andrefelipecp")) {
				log.info(userFind.toString());
			}
            log.info("");
		};
	}
    
}
