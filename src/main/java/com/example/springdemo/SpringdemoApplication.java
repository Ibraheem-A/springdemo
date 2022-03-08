package com.example.springdemo;

import com.example.springdemo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);
	}

	@GetMapping
	public List<Student> hello() {
		return  List.of(
				new Student(
						1L,
						"John",
						"johndoe@gmail.com",
						LocalDate.of(2000, Month.JANUARY, 10),
						22)
		);
	}

}
