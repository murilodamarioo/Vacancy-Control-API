package com.rocketseat.vacancy_control;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Vacancy Control",
				description = "API responsible for vacancy control",
				version = "1"
		)
)
public class VacancyControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyControlApplication.class, args);
	}

}
