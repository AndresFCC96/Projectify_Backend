package com.adsforgood.projectify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan ({"com.adsforgoodprojectify.", "com.adsforgood.projectify.config"})
//@EnableJpaRepositories("com.adsforgood.projectify.repository")
//@EnableAutoConfiguration
public class ProjectifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectifyApplication.class, args);
	}

}
