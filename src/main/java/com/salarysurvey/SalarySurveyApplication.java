package com.salarysurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SalarySurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalarySurveyApplication.class, args);
	}

}
