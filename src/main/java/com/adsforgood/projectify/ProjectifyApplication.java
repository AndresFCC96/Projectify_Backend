package com.adsforgood.projectify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan ({"com.adsforgoodprojectify.", "com.adsforgood.projectify.config"})
//@EnableJpaRepositories("com.adsforgood.projectify.repository")
//@EnableAutoConfiguration
public class ProjectifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectifyApplication.class, args);
	}

}
