package com.example.springdemo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {
    public List<Student> getStudents() {
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
