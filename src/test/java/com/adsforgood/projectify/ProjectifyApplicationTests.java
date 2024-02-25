package com.adsforgood.projectify;

import com.adsforgood.projectify.config.JwtAuthenticationFilter;
import com.adsforgood.projectify.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectifyApplicationTests {

	@Test
	void contextLoads() {
	}

}
