package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        /*org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class*/}
        )
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.repositories")
@EntityScan("com.revature.beans")
public class Project2WorkoutAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2WorkoutAppApplication.class, args);
	}

}
