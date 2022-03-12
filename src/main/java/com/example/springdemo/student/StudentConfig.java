package com.example.springdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student john = new Student(
                    "John",
                    "johndoe@gmail.com",
                    LocalDate.of(2000, JANUARY, 10),
                    22);
            Student jane = new Student(
                    "Jane",
                    "janedane@gmail.com",
                    LocalDate.of(2002, JANUARY, 20),
                    20);
            studentRepository.saveAll(List.of(john, jane));
        };
    }
}
