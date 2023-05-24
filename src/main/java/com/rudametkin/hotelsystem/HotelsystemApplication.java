package com.rudametkin.hotelsystem;

import com.rudametkin.hotelsystem.database.jpadao.JPADAOFactory;
import com.rudametkin.hotelsystem.database.jpadao.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JPADAOFactory jpadaoFactory) {
		return args -> {
			System.out.println(jpadaoFactory.getUserDAO().findByLogin("VyaSik"));
		};
	}

}
