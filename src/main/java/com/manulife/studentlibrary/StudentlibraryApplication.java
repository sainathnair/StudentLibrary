package com.manulife.studentlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StudentlibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentlibraryApplication.class, args);
	}

}
