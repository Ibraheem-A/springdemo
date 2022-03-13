package com.example.springdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @PutMapping (path="update={studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name){
        studentService.updateStudent(studentId, email, name);
    }

    @DeleteMapping (path ="delete={studentNameOrId}")
    public void deleteStudent(@PathVariable("studentNameOrId") String studentNameOrId){
        studentService.deleteStudentByNameOrId(studentNameOrId);
    }
}
